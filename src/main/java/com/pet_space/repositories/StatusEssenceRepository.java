package com.pet_space.repositories;

import com.pet_space.models.essences.StatusEssence;
import org.springframework.data.repository.CrudRepository;

public interface StatusEssenceRepository extends CrudRepository<StatusEssence, StatusEssence.StatusEssenceEnum> {
}
