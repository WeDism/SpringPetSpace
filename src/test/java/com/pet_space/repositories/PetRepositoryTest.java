package com.pet_space.repositories;

import org.junit.Test;

import static com.pet_space.repositories.PetRepositoryTestData.PET_PERS;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.junit.Assert.assertNotNull;

public class PetRepositoryTest extends DbInit {

    @Test
    public void findByNameAndOwner() {
        assertNotNull(this.petRepository.findByNameAndOwner(PET_PERS.getName(), USER_ESSENCE_JOHN));
    }
}