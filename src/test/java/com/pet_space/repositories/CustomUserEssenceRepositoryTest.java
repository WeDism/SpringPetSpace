package com.pet_space.repositories;

import com.pet_space.models.essences.UserEssence;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.pet_space.repositories.PetRepositoryTestData.SET_PETS;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CustomUserEssenceRepositoryTest extends DbInit {

    @Test
    public void deleteCascade() {
        //3c0fca6c-95b7-4a06-8a56-3c9f5074f6ee 3804de7a-8331-47a8-b460-7d1137c0f634 18676776-fc9f-4986-a495-ffe4479cf24a
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

    @Test
    public void findFriend() {
        List<UserEssence> userEssences = this.customUserEssenceRepository
                .fiendFriend(USER_ESSENCE_JOHN, USER_ESSENCE_FRED.getName(), USER_ESSENCE_FRED.getSurname(), USER_ESSENCE_FRED.getPatronymic());
        assertThat(userEssences.size(), is(1));
        userEssences = this.customUserEssenceRepository.fiendFriend(USER_ESSENCE_JOHN, USER_ESSENCE_SIMON.getName(), null, null);
        assertThat(userEssences.size(), is(1));
        userEssences = this.customUserEssenceRepository.fiendFriend(USER_ESSENCE_FRED, USER_ESSENCE_SIMON.getName(), USER_ESSENCE_SIMON.getSurname(), null);
        assertThat(userEssences.size(), is(1));
        userEssences = this.customUserEssenceRepository.fiendFriend(USER_ESSENCE_FRED, null, null, "test");
        assertThat(userEssences.size(), is(0));
        userEssences = this.customUserEssenceRepository.fiendFriend(USER_ESSENCE_FRED, null, "name", "test");
        assertThat(userEssences.size(), is(0));
    }


}