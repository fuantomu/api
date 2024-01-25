package de.raidcomp.websocket;

//import java.util.ArrayList;
//import java.util.List;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.raidcomp.data.entity.MessageEntity;
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
  // private List<String> messageHistory = new ArrayList<String>();

  private final WebSocketBroadcaster broadcaster;
  private final MessageRepository messageRepository;

  public UpdateWebSocket(WebSocketBroadcaster broadcaster, MessageRepository messageRepository) {
    this.broadcaster = broadcaster;
    this.messageRepository = messageRepository;
  }

  @OnOpen
  public Publisher<String> onOpen(WebSocketSession session) {
    log("onOpen", session);
    return broadcaster.broadcast("{\"message_type\":\"update\"}");
    // return
    // broadcaster.broadcast(String.format("{\"messageType\":\"update\",\"data\":%s}",
    // messageHistory.subList(Math.max(messageHistory.size() - 20, 0),
    // messageHistory.size())));
  }

  @OnMessage
  public Publisher<String> onMessage(
      String message,
      WebSocketSession session) {

    log("onMessage", session);
    MessageEntity newMessage = new MessageEntity();
    JSONObject messageObject = new JSONObject(message);

    newMessage.setAccount_name(messageObject.get("account_name").toString());
    newMessage.setData(messageObject.get("data").toString());
    newMessage.setDate(Long.parseLong(messageObject.get("date").toString()));
    newMessage.setMessage_type(messageObject.get("message_type").toString());
    newMessage.setId(UUID.randomUUID().toString());
    messageRepository.save(newMessage);
    // messageHistory.add(message);
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
