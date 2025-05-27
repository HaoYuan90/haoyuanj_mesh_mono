package com.haoyuanj.mesh.chat;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

public class ChatSessionHandler implements StompSessionHandler {

  @Override
  public Type getPayloadType(StompHeaders headers) {
    return String.class;
  }

  @Override
  public void handleFrame(StompHeaders headers, Object payload) {
    System.out.println(payload.toString());
  }

  @Override
  public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
    System.out.println("Connected.");
  }

  @Override
  public void handleException(
      StompSession session,
      StompCommand command,
      StompHeaders headers,
      byte[] payload,
      Throwable exception) {
    System.out.println("Exception caught: " + exception.toString());
  }

  @Override
  public void handleTransportError(StompSession session, Throwable exception) {
    System.out.println("Transport error: " + exception.toString());
  }
}
