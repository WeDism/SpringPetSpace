package com.pet_space.json_customs.json_serializers;

import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageOfUserId;
import com.pet_space.models.messages.MessageState;
import com.pet_space.repositories.DbInit;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import java.io.IOException;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.NEW;
import static com.pet_space.repositories.MessageTestData.MESSAGE_FIRST;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_SIMON;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MessageDeserializerAndSerializerTest extends DbInit {

    @Test
    public void compare() throws IOException {
        Message cloneMessageFirst = SerializationUtils.clone(MESSAGE_FIRST);
        cloneMessageFirst.getOwners().add(new MessageOfUser(MessageOfUserId.of(USER_ESSENCE_SIMON, cloneMessageFirst), MessageState.of(NEW)));
        cloneMessageFirst.getOwners().add(new MessageOfUser(MessageOfUserId.of(USER_ESSENCE_FRED, cloneMessageFirst), MessageState.of(NEW)));
        String jsonMessageFirst = this.objectMapper.writeValueAsString(cloneMessageFirst);
        Message message = this.objectMapper.readValue(jsonMessageFirst, Message.class);
        assertThat(message, is(cloneMessageFirst));
    }
}