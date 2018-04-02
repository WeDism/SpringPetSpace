package com.pet_space.services;

import com.pet_space.models.Message;

public interface CustomMessageRepository {
    void save(Message message);
    void deleteAll();
}
