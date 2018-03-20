package com.pet_space.repositories;

import com.pet_space.models.essences.UserEssence;
import org.junit.Before;
import org.junit.Test;

import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_CAT;
import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_DOG;
import static com.pet_space.repositories.RoleEssenceRepositoryTestData.*;
import static com.pet_space.repositories.StateFriendRepositoryTestData.*;
import static com.pet_space.repositories.StatusEssenceRepositoryTestData.*;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserEssenceRepositoryTest extends DbInit {
    @Before
    public void setUp() {
        this.roleEssenceRepository.save(ROLE_ESSENCE_ROOT);
        this.roleEssenceRepository.save(ROLE_ESSENCE_ADMIN);
        this.roleEssenceRepository.save(ROLE_ESSENCE_USER);

        this.statusEssenceRepository.save(STATUS_ESSENCE_ACTIVE);
        this.statusEssenceRepository.save(STATUS_ESSENCE_DELETED);
        this.statusEssenceRepository.save(STATUS_ESSENCE_INACTIVE);

        this.stateFriendRepository.save(STATE_FRIEND_APPROVED);
        this.stateFriendRepository.save(STATE_FRIEND_REJECTED);
        this.stateFriendRepository.save(STATE_FRIEND_REQUESTED);


        this.genusPetRepository.save(GENUS_CAT);
        this.genusPetRepository.save(GENUS_DOG);

        this.userEssenceRepository.save(USER_ESSENCE_JOHN.setUserEssenceId(null));
    }

    @Test
    public void findByNickname() {
        UserEssence byNickname = this.userEssenceRepository.findByNickname(USER_ESSENCE_JOHN.getNickname()).get();
        assertThat(byNickname, is(USER_ESSENCE_JOHN));
    }

}
