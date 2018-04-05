package com.pet_space.repositories;

import com.pet_space.models.messages.MessageState;

public interface MessageStateRepositoryTestData {
    MessageState MESSAGE_STATE_NEW = MessageState.of(MessageState.MessageStateEnum.NEW);
    MessageState MESSAGE_STATE_VIEWED = MessageState.of(MessageState.MessageStateEnum.VIEWED);
}
