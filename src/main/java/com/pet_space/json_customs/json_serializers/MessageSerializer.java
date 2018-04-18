package com.pet_space.json_customs.json_serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pet_space.models.messages.Message;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MessageSerializer extends JsonSerializer<Message> {
    @Override
    public void serialize(Message value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        if (value.getMessageId() == null) gen.writeNullField("messageId");
        else gen.writeStringField("messageId", value.getMessageId().toString());
        if (value.getText() == null) gen.writeNullField("text");
        else gen.writeStringField("text", value.getText());
        if (value.getDate() == null) gen.writeNullField("date");
        else gen.writeStringField("date", value.getDate().toString());
        if (value.getAuthor() == null) gen.writeNullField("author");
        else gen.writeStringField("author", value.getAuthor().getNickname());
        List<String> collect = value.getOwners().stream().map(o -> o.getUserEssence().getName()).collect(Collectors.toList());
        gen.writeObjectField("owners", collect);
        gen.writeEndObject();
    }
}
