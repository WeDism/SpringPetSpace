package com.pet_space.repositories;

import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageOfUserId;
import org.springframework.data.repository.CrudRepository;

public interface MessageOfUserRepository extends CrudRepository<MessageOfUser, MessageOfUserId> {
}
