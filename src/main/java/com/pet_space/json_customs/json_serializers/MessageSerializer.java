package com.pet_space.json_customs.json_serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageOfUser;

import java.io.IOException;

public class MessageSerializer extends JsonSerializer<Message> {
    @Override
    public void serialize(Message value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("messageId", value.getMessageId().toString());
        gen.writeStringField("text", value.getText());
        gen.writeStringField("date", value.getDate().toString());
        gen.writeStringField("author", value.getAuthor().getNickname());
        gen.writeArrayFieldStart("owners");
        gen.writeStartArray();
        for (MessageOfUser messageOfUser : value.getOwners()) {
            gen.writeString(messageOfUser.getUserEssence().getNickname());
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
