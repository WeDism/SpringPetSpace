package com.pet_space.json_customs.json_deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageOfUserId;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Strings.isNullOrEmpty;

public class MessageDeserializer extends JsonDeserializer<Message> {
    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String messageIdAsString = node.get("messageId").textValue();
        String text = node.get("text").textValue();
        String dateAsString = node.get("date").textValue();
        String authorAsString = node.get("author").textValue();
        JsonNode ownersAsArray = node.withArray("owners");

        LocalDateTime date = isNullOrEmpty(dateAsString) ? null : LocalDateTime.parse(dateAsString);
        UUID messageId = isNullOrEmpty(messageIdAsString) ? null : UUID.fromString(messageIdAsString);
        UserEssence author = isNullOrEmpty(authorAsString) ? null : new UserEssence().setNickname(authorAsString);
        List<MessageOfUser> owners = new ArrayList<>();
        Message message = new Message();
        if (!ownersAsArray.isNull()) {
            for (JsonNode owner : ownersAsArray) {
                owners.add(new MessageOfUser().setPrimaryKey(
                        new MessageOfUserId()
                                .setOwner(new UserEssence().setNickname(owner.textValue()))
                                .setMessage(message))
                );
            }
        }
        return message.setMessageId(messageId).setText(text).setDate(date).setAuthor(author).setOwners(owners);
    }
}
