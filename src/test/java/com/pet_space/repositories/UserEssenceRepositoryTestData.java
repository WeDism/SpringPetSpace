package com.pet_space.repositories;

import com.pet_space.models.essences.RoleEssence;
import com.pet_space.models.essences.StatusEssence;
import com.pet_space.models.essences.UserEssence;

public interface UserEssenceRepositoryTestData {
    UserEssence USER_ESSENCE_JOHN = UserEssence.builder()
            .nickname("jo")
            .name("John")
            .surname("Jo")
            .role(new RoleEssence(RoleEssence.RoleEssenceEnum.ADMIN))
            .statusEssence(new StatusEssence(StatusEssence.StatusEssenceEnum.ACTIVE))
            .email("jo@jo.com")
            .password("pass")
            .build();
    UserEssence USER_ESSENCE_FRED = UserEssence.builder()
            .nickname("fred")
            .name("Fred")
            .surname("Fr")
            .role(new RoleEssence(RoleEssence.RoleEssenceEnum.USER))
            .statusEssence(new StatusEssence(StatusEssence.StatusEssenceEnum.ACTIVE))
            .email("fr@fr.com")
            .password("pass")
            .build();
    UserEssence USER_ESSENCE_SIMON = UserEssence.builder()
            .nickname("sai")
            .name("Simon")
            .surname("Si")
            .role(new RoleEssence(RoleEssence.RoleEssenceEnum.USER))
            .statusEssence(new StatusEssence(StatusEssence.StatusEssenceEnum.ACTIVE))
            .email("si@si.com")
            .password("pass")
            .build();
}
