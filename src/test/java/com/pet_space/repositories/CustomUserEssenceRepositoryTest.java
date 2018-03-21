package com.pet_space.repositories;

import com.pet_space.models.essences.UserEssence;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_CAT;
import static com.pet_space.repositories.GenusPetRepositoryTestData.GENUS_DOG;
import static com.pet_space.repositories.PetRepositoryTestData.*;
import static com.pet_space.repositories.RoleEssenceRepositoryTestData.*;
import static com.pet_space.repositories.StateFriendRepositoryTestData.*;
import static com.pet_space.repositories.StatusEssenceRepositoryTestData.*;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CustomUserEssenceRepositoryTest extends DbInit {

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

        this.userEssenceRepository.save(USER_ESSENCE_JOHN);
        this.userEssenceRepository.save(USER_ESSENCE_FRED);
        this.userEssenceRepository.save(USER_ESSENCE_SIMON);

        this.petRepository.save(PET_LAYMA);
        this.petRepository.save(PET_PERS);
        this.petRepository.save(PET_TIMON);
        this.petRepository.save(PET_TOSH);
    }

    @Test
    public void deleteCascade() {

        UserEssence userEssenceFred = this.userEssenceRepository.findOne(USER_ESSENCE_JOHN.getUserEssenceId());
        UserEssence userEssenceJohn = this.userEssenceRepository.findOne(USER_ESSENCE_FRED.getUserEssenceId());
        UserEssence userEssenceSimon = this.userEssenceRepository.findOne(USER_ESSENCE_SIMON.getUserEssenceId());

        assertThat(userEssenceFred.getPets(), is(SET_PETS.stream().filter(pet -> pet.getOwner().equals(userEssenceFred)).collect(Collectors.toSet())));
        assertThat(userEssenceJohn.getPets(), is(SET_PETS.stream().filter(pet -> pet.getOwner().equals(userEssenceJohn)).collect(Collectors.toSet())));
        assertThat(userEssenceSimon.getPets(), is(SET_PETS.stream().filter(pet -> pet.getOwner().equals(userEssenceSimon)).collect(Collectors.toSet())));

        this.customUserEssenceRepository.deleteCascade(userEssenceFred);
        this.customUserEssenceRepository.deleteCascade(userEssenceJohn);
        this.customUserEssenceRepository.deleteCascade(userEssenceSimon);

        assertNull(this.userEssenceRepository.findOne(USER_ESSENCE_JOHN.getUserEssenceId()));
        assertNull(this.userEssenceRepository.findOne(USER_ESSENCE_FRED.getUserEssenceId()));
        assertNull(this.userEssenceRepository.findOne(USER_ESSENCE_SIMON.getUserEssenceId()));
    }



}