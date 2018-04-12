package com.pet_space.services;


import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageState;

import java.util.List;
import java.util.UUID;

public interface CustomUserEssenceRepository {

    void deleteCascadeById(UUID id);

    void deleteCascade(UserEssence entity);

    List<UserEssence> fiendFriend(UserEssence userEssence, String name, String surname, String patronymic);

}
