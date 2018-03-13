package com.pet_space.repositories;

import com.pet_space.models.essences.StatusEssence;

public interface StatusEssenceRepositoryTestData {
    StatusEssence STATUS_ESSENCE_ACTIVE = new StatusEssence(StatusEssence.StatusEssenceEnum.ACTIVE);
    StatusEssence STATUS_ESSENCE_DELETED = new StatusEssence(StatusEssence.StatusEssenceEnum.DELETED);
    StatusEssence STATUS_ESSENCE_INACTIVE = new StatusEssence(StatusEssence.StatusEssenceEnum.INACTIVE);
}
