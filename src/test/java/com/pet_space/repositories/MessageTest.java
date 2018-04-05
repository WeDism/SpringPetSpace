package com.pet_space.repositories;

import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageOfUserId;
import com.pet_space.models.messages.MessageState;
import org.junit.Before;
import org.junit.Test;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.NEW;
import static com.pet_space.repositories.MessageTestData.MESSAGE_FIRST;
import static com.pet_space.repositories.MessageTestData.MESSAGE_SECOND;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MessageTest extends DbInit {
    @Override
    @Before
    public void setUp() {
        super.setUp();

        MessageOfUser messageFirstForFred = new MessageOfUser(MessageOfUserId.of(USER_ESSENCE_FRED, MESSAGE_FIRST), MessageState.of(NEW));
        MessageOfUser messageSecondForFred = new MessageOfUser(MessageOfUserId.of(USER_ESSENCE_FRED, MESSAGE_SECOND), MessageState.of(NEW));
        MessageOfUser messageSecondForSimon = new MessageOfUser(MessageOfUserId.of(USER_ESSENCE_SIMON, MESSAGE_SECOND), MessageState.of(NEW));

        this.messageOfUserRepository.save(messageFirstForFred);
        this.messageOfUserRepository.save(messageSecondForFred);
        this.messageOfUserRepository.save(messageSecondForSimon);
    }

    @Test
    public void checkMessagesOfUser() {

        assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_FRED.getUserEssenceId()).getMessagesTo().size(), is(2));
        assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_SIMON.getUserEssenceId()).getMessagesTo().size(), is(1));
        assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_JOHN.getUserEssenceId()).getMessagesFrom().size(), is(2));
    }
}
