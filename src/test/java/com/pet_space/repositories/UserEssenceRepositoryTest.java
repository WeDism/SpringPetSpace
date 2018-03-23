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

    @Test
    public void findByNickname() {
        UserEssence byNickname = this.userEssenceRepository.findByNickname(USER_ESSENCE_JOHN.getNickname());
        assertThat(byNickname, is(USER_ESSENCE_JOHN));
    }

}
