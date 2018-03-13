package com.pet_space.repositories;

import com.pet_space.models.essences.RoleEssence;
import org.springframework.data.repository.CrudRepository;

public interface RoleEssenceRepository extends CrudRepository<RoleEssence, RoleEssence.RoleEssenceEnum> {
}
