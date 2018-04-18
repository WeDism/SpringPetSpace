package com.pet_space.repositories;

import com.pet_space.models.essences.UserEssence;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.pet_space.repositories.PetRepositoryTestData.PET_PERS;
import static com.pet_space.repositories.PetRepositoryTestData.PET_TIMON;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_SIMON;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FollowersPetsTest extends DbInit {

    @Test
    public void FollowByPetsUserEssencePetWithInitLazyObject() {
        USER_ESSENCE_FRED.setFollowByPets(new HashSet<>(Arrays.asList(PET_PERS, PET_TIMON)));
        this.userEssenceRepository.save(USER_ESSENCE_FRED);

        assertThat(this.userEssenceRepository.findOneWithEagerFollowedPets(USER_ESSENCE_FRED.getUserEssenceId()).getFollowByPets().size(), is(2));
    }

    @Test
    public void followedPetWithInitLazyUserEssence() {
        Set<UserEssence> followersPet = PET_PERS.getFollowersPet();
        followersPet.add(USER_ESSENCE_FRED);
        followersPet.add(USER_ESSENCE_SIMON);
        this.petRepository.save(PET_PERS);

        assertThat(this.petRepository.findPetWithEagerObjects(PET_PERS.getPetId()).getFollowersPet().size(), is(2));
    }
}
