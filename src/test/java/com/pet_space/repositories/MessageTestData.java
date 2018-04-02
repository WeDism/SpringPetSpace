package com.pet_space.repositories;

import com.pet_space.models.Message;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;

public interface MessageTestData {
    Message MESSAGE_FIRST = new Message()
            .setDate(LocalDateTime.now())
            .setText("Hi Fred it's test message")
            .setAuthor(USER_ESSENCE_JOHN)
            .setOwners(new HashSet<>(Arrays.asList(USER_ESSENCE_FRED)));
    Message MESSAGE_SECOND = new Message()
            .setDate(LocalDateTime.now())
            .setText("Hi all it's test message")
            .setAuthor(USER_ESSENCE_JOHN)
            .setOwners(new HashSet<>(Arrays.asList(USER_ESSENCE_FRED, USER_ESSENCE_SIMON)));

}
