package com.pet_space.repositories;

import com.pet_space.controllers.ControllerInit;
import com.pet_space.models.essences.UserEssence;
import org.junit.Test;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserEssenceRepositoryTest extends ControllerInit {

    @Test
    public void findByNickname() {
        UserEssence byNickname = this.userEssenceRepository.findByNickname(USER_ESSENCE_JOHN.getNickname());
        assertThat(byNickname, is(USER_ESSENCE_JOHN));
    }

    @Test
    public void existsByEmail() {
        boolean existsByEmail = this.userEssenceRepository.existsByEmail(USER_ESSENCE_JOHN.getEmail());
        assertTrue(existsByEmail);

        existsByEmail = this.userEssenceRepository.existsByEmail("not-exist@email.com");
        assertFalse(existsByEmail);
    }

    @Test
    public void existsByNickname() {
        boolean existsByNickname = this.userEssenceRepository.existsByNickname(USER_ESSENCE_JOHN.getNickname());
        assertTrue(existsByNickname);

        existsByNickname = this.userEssenceRepository.existsByNickname("nickname");
        assertFalse(existsByNickname);
    }
}
