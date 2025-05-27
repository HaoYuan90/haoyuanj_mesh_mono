package com.haoyuanj.mesh.chat;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Main {
  public static void main(String[] args) throws InterruptedException, IOException {
    WebSocketClient chatClient = new StandardWebSocketClient();
    WebSocketHandler socketHandler = new TextWebSocketHandler();
    CompletableFuture<WebSocketSession> sessionFute =
        chatClient.execute(socketHandler, "ws://localhost:55004/ws/temp");
    WebSocketSession chatSession = null;
    try {
      chatSession = sessionFute.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (chatSession == null) {
      return;
    }

    MessageConverter mc = new MappingJackson2MessageConverter();

    chatSession.sendMessage(new TextMessage(("haha").getBytes()));
  }
}
