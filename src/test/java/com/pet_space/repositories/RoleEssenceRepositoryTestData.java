package com.pet_space.repositories;

import com.pet_space.models.essences.RoleEssence;

public interface RoleEssenceRepositoryTestData {
    RoleEssence ROLE_ESSENCE_ADMIN = new RoleEssence(RoleEssence.RoleEssenceEnum.ADMIN);
    RoleEssence ROLE_ESSENCE_ROOT = new RoleEssence(RoleEssence.RoleEssenceEnum.ROOT);
    RoleEssence ROLE_ESSENCE_USER = new RoleEssence(RoleEssence.RoleEssenceEnum.USER);
}
