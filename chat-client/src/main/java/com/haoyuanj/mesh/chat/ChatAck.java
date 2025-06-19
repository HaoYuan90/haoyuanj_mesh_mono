package com.haoyuanj.mesh.chat;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonTypeName("ack")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChatAck(String messageId, long timestampMili) {}
