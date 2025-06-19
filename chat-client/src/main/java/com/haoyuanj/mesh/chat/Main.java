package com.haoyuanj.mesh.chat;

import java.util.Scanner;
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

  private static String collectChannelName(Scanner sc) {
    System.out.println("Please input chat channel name:");
    return sc.nextLine().toLowerCase().trim();
  }

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    String channelName = collectChannelName(sc);

    WebSocketClient chatClient = new StandardWebSocketClient();
    WebSocketHandler socketHandler = new ChatMsgWebSocketHandler();
    CompletableFuture<WebSocketSession> sessionFute =
        chatClient.execute(socketHandler, "ws://localhost:4000/ws/" + channelName);
    WebSocketSession chatSession = null;
    try {
      chatSession = sessionFute.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (chatSession == null) {
      return;
    }

    while(true) {
      System.out.print("You: ");
      String msg = sc.nextLine().trim();
      chatSession.sendMessage(getChatMessage(msg));
    }

    // Program can only be closed by ctrl-c
  }
}
