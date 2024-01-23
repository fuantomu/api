package de.raidcomp.websocket;

import java.util.function.Predicate;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;

@ServerWebSocket("/ws/update/{type}")
public class UpdateWebSocket {

  private static final Logger LOG = LoggerFactory.getLogger(UpdateWebSocket.class);

  private final WebSocketBroadcaster broadcaster;

  public UpdateWebSocket(WebSocketBroadcaster broadcaster) {
    System.out.println(broadcaster);
    this.broadcaster = broadcaster;
  }

  @OnOpen
  public Publisher<String> onOpen(String type, WebSocketSession session) {
    log("onOpen", session, type);
    if (type.equals("all")) {
      return broadcaster.broadcast(String.format("[%s] Now making announcements!", type), isValid(type));
    }
    return broadcaster.broadcast(String.format("[%s] Joined!", type), isValid(type));
  }

  @OnMessage
  public Publisher<String> onMessage(
      String type,
      String message,
      WebSocketSession session) {

    log("onMessage", session, type);
    return broadcaster.broadcast(String.format("[%s] %s", type, message), isValid(type));
  }

  @OnClose
  public Publisher<String> onClose(
      String type,
      WebSocketSession session) {

    log("onClose", session, type);
    return broadcaster.broadcast(String.format("[%s] Leaving!", type), isValid(type));
  }

  private void log(String event, WebSocketSession session, String type) {
    LOG.info("* WebSocket: {} received for session {} from '{}'",
        event, session.getId(), type);
  }

  private Predicate<WebSocketSession> isValid(String type) {
    return s -> type.equals("all") // broadcast to all users
        || "all".equals(s.getUriVariables().get("type", String.class, null)) // "all" subscribes to every topic
        || type.equalsIgnoreCase(s.getUriVariables().get("type", String.class, null)); // intra-topic chat
  }
}
