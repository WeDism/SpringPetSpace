package com.pet_space.repositories;

import com.pet_space.models.messages.MessageState;
import org.springframework.data.repository.CrudRepository;

public interface MessageStateRepository extends CrudRepository<MessageState, MessageState.MessageStateEnum> {
}
