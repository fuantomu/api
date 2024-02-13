package de.raidcomp.websocket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import de.raidcomp.data.entity.MessageEntity;
import de.raidcomp.data.entity.UserEntity;
import de.raidcomp.data.repository.MessageRepository;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;

import org.json.JSONObject;

import java.util.UUID;

@ServerWebSocket("/wss/update")
public class UpdateWebSocket {

  private static final Logger LOG = LoggerFactory.getLogger(UpdateWebSocket.class);

  private final WebSocketBroadcaster broadcaster;
  private final MessageRepository messageRepository;

  final Integer LOGOUT_TIME = 60000;

  private List<UserEntity> users = new ArrayList<UserEntity>();

  public UpdateWebSocket(WebSocketBroadcaster broadcaster, MessageRepository messageRepository) {
    this.broadcaster = broadcaster;
    this.messageRepository = messageRepository;
  }

  @OnOpen
  public Publisher<MessageEntity> onOpen(WebSocketSession session) {
    log("onOpen", session);
    MessageEntity newMessage = new MessageEntity();
    newMessage.setMessage_type("users");
    newMessage.setData(new Gson().toJson(users.toArray()));
    return broadcaster.broadcast(newMessage);
  }

  @OnMessage
  public Publisher<String> onMessage(
      String message,
      WebSocketSession session) {

    log("onMessage", session);

    MessageEntity newMessage = new MessageEntity();
    JSONObject messageObject = new JSONObject(message);

    if (messageObject.get("message_type").toString().equals("heartbeat")) {
      Optional<UserEntity> existingUser = users.stream()
          .filter(user -> user.getHost().equals(messageObject.get("host"))).findFirst();
      if (existingUser.isPresent()) {
        existingUser.get().setLast_message(System.currentTimeMillis());
      }

      Iterator<UserEntity> iterator = users.iterator();
      while (iterator.hasNext()) {
        UserEntity user = iterator.next();
        if (System.currentTimeMillis() - user.getLast_message() >= LOGOUT_TIME) {
          messageObject.put("message_type", "logout");
          messageObject.put("data",
              new JSONObject().put("host", user.getHost()).put("username", user.getUsername()));
          messageObject.put("socketId", user.getHost());
          iterator.remove();
          return broadcaster.broadcast(messageObject.toString());

        }
      }

      return broadcaster.broadcast("{\"message_type\": \"heartbeat\"}");
    }

    if (messageObject.get("message_type").toString().equals("login")) {
      UserEntity newUser = new Gson().fromJson(messageObject.get("data").toString(), UserEntity.class);

      if (users.stream().filter(user -> user.getHost().equals(newUser.getHost())).findFirst().isEmpty()) {
        newUser.setLast_message(System.currentTimeMillis());
        users.add(newUser);
      }
      newMessage.setMessage_type("login");
      messageObject.put("data", new Gson().toJson(users.toArray()));
      return broadcaster.broadcast(messageObject.toString());
    }
    if (messageObject.get("message_type").toString().equals("logout")) {
      UserEntity existingUser = new Gson().fromJson(messageObject.get("data").toString(), UserEntity.class);
      users.remove(existingUser);
      return broadcaster.broadcast(String.format("%s", message));
    }

    newMessage.setAccount_name(messageObject.optString("account_name", "System"));
    newMessage.setData(messageObject.get("data").toString());
    newMessage.setDate(Long.parseLong(messageObject.optString("date", String.valueOf(System.currentTimeMillis()))));
    newMessage.setMessage_type(messageObject.get("message_type").toString());
    newMessage.setId(UUID.randomUUID().toString());
    newMessage.setVersion(messageObject.optString("version", "System"));
    messageRepository.save(newMessage);

    return broadcaster.broadcast(String.format("%s", message));
  }

  @OnClose
  public void onClose(
      WebSocketSession session) {

    log("onClose", session);
  }

  private void log(String event, WebSocketSession session) {
    LOG.info("* WebSocket: {} received for session {}",
        event, session.getId());
  }

}
