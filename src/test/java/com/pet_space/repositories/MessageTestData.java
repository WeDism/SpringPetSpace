package com.pet_space.repositories;

import com.pet_space.models.messages.Message;

import java.time.LocalDateTime;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;

public interface MessageTestData {
    Message MESSAGE_FIRST = new Message()
            .setDate(LocalDateTime.now())
            .setText("Hi Fred it's test message")
            .setAuthor(USER_ESSENCE_JOHN);
    Message MESSAGE_SECOND = new Message()
            .setDate(LocalDateTime.now())
            .setText("Hi all it's test message")
            .setAuthor(USER_ESSENCE_JOHN);
}
