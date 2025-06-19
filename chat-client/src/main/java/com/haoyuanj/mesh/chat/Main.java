package com.haoyuanj.mesh.chat;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import java.time.Instant;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Main {

  private static final ObjectMapper m = new ObjectMapper();

  private static TextMessage getChatMessage(String msgStr) throws JsonProcessingException {
    ChatData msg =
        new ChatData(
            new ChatMessage(UUID.randomUUID().toString(), msgStr, Instant.now().toEpochMilli()),
            null);
    return new TextMessage(m.writeValueAsBytes(msg));
  }

  public static void main(String[] args)
      throws InterruptedException, IOException, JsonProcessingException {
    WebSocketClient chatClient = new StandardWebSocketClient();
    WebSocketHandler socketHandler = new ChatMsgWebSocketHandler();
    CompletableFuture<WebSocketSession> sessionFute =
        chatClient.execute(socketHandler, "ws://localhost:55003/ws/temp");
    WebSocketSession chatSession = null;
    try {
      chatSession = sessionFute.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (chatSession == null) {
      return;
    }

    chatSession.sendMessage(getChatMessage("haha"));
    Thread.sleep(5000);
  }
}
