package com.haoyuanj.mesh.chat;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonTypeName("message")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChatMessage(String messageId, String content, long timestampMili) {}
