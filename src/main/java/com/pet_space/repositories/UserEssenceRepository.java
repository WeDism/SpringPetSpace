package com.pet_space.repositories;

import com.pet_space.models.essences.UserEssence;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserEssenceRepository extends CrudRepository<UserEssence, UUID> {
}
