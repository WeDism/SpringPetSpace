package com.pet_space.custom_repositories;


import com.pet_space.models.essences.UserEssence;

import java.util.List;
import java.util.UUID;

public interface CustomUserEssenceRepository {

    void deleteCascadeById(UUID id);

    void deleteCascade(UserEssence entity);

    List<UserEssence> fiendFriend(UserEssence userEssence, String name, String surname, String patronymic);

}
