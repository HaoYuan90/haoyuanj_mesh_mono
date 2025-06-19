package com.haoyuanj.mesh.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatMsgWebSocketHandler extends TextWebSocketHandler {

  private static final Logger logger = LoggerFactory.getLogger(ChatMsgWebSocketHandler.class);
  private static final ObjectMapper m = new ObjectMapper();

  private void handleChatData(ChatData data) throws Exception {
    // Expect the data to contain only one of message and ack
    if (data.message() != null && data.ack() != null) {
      throw new Exception("Unexpected message: Expect one of message and ack, not both.");
    }
    if (data.message() != null) {
      handleMessage(data.message());
    } else if (data.ack() != null) {
      handleAck(data.ack());
    }
  }

  private void handleMessage(ChatMessage msg) {
    System.out.print("Them: ");
    System.out.println(msg.content());
  }

  private void handleAck(ChatAck ack) {
    logger.debug("Received ack {}", ack);
    // TODO: handle message ack
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    ChatData data = m.readValue(message.getPayload(), ChatData.class);
    try {
      handleChatData(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
