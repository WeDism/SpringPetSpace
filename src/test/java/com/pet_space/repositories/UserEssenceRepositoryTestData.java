package com.pet_space.repositories;

import com.pet_space.models.essences.RoleEssence;
import com.pet_space.models.essences.StatusEssence;
import com.pet_space.models.essences.UserEssence;

import java.time.LocalDateTime;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.*;
import static com.pet_space.models.essences.StatusEssence.StatusEssenceEnum.ACTIVE;

public interface UserEssenceRepositoryTestData {
    UserEssence USER_ESSENCE_JOHN = UserEssence.builder()
            .nickname("jo")
            .name("John")
            .surname("Jo")
            .role(new RoleEssence(ADMIN))
            .statusEssence(new StatusEssence(ACTIVE))
            .email("jo@jo.com")
            .password("password")
            .build();
    UserEssence USER_ESSENCE_FRED = UserEssence.builder()
            .nickname("fred")
            .name("Fred")
            .surname("Fr")
            .role(new RoleEssence(USER))
            .statusEssence(new StatusEssence(ACTIVE))
            .email("fr@fr.com")
            .password("password")
            .birthday(LocalDateTime.now())
            .build();
    UserEssence USER_ESSENCE_SIMON = UserEssence.builder()
            .nickname("sai")
            .name("Simon")
            .surname("Si")
            .role(new RoleEssence(USER))
            .statusEssence(new StatusEssence(ACTIVE))
            .email("si@si.com")
            .password("password")
            .build();

    UserEssence USER_ESSENCE_BART = UserEssence.builder()
            .nickname("bart")
            .name("Bart")
            .surname("Ba")
            .role(new RoleEssence(ROOT))
            .statusEssence(new StatusEssence(ACTIVE))
            .email("ba@ba.com")
            .password("bart")
            .build();
}
