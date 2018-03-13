package com.pet_space.services;


import com.pet_space.models.essences.UserEssence;

import java.util.UUID;

public interface CustomUserEssenceRepository {

    void deleteCascadeById(UUID id);

    void deleteCascade(UserEssence entity);

}
