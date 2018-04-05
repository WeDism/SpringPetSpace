package com.pet_space.controllers;

import com.pet_space.models.pets.Pet;
import com.pet_space.models.essences.UserEssence;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Set;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class FriendControllerIntegrationTest extends ControllerInit {
    @Autowired
    private FriendController friendController;

    @Test
    public void postFriendRequest() {
        UserEssence userEssenceJohn = SerializationUtils.clone(USER_ESSENCE_JOHN).setNickname("JOHN_CLONE");
        Set<Pet> petsJohn = userEssenceJohn.getPets();
        userEssenceJohn.setPets(Collections.emptySet());

        UserEssence userEssenceFred = SerializationUtils.clone(USER_ESSENCE_FRED).setNickname("FRED_CLONE");
        Set<Pet> petsFred = userEssenceFred.getPets();
        userEssenceFred.setPets(Collections.emptySet());
        this.userEssenceRepository.save(userEssenceJohn);
        this.userEssenceRepository.save(userEssenceFred);

        petsJohn.forEach(e -> {
            e.setPetId(randomUUID());
            e.setOwner(userEssenceJohn);
        });
        this.petRepository.save(petsJohn);
        userEssenceJohn.setPets(petsJohn);

        petsFred.forEach(e -> {
            e.setPetId(randomUUID());
            e.setOwner(userEssenceFred);
        });
        this.petRepository.save(petsFred);
        userEssenceFred.setPets(petsFred);

        when(this.authentication.getName()).thenReturn(userEssenceFred.getNickname());
        assertThat(friendController.postFriendRequest(userEssenceJohn.getUserEssenceId(), this.authentication).getStatusCode(), is(HttpStatus.CREATED));
        assertThat(this.userEssenceRepository.findOne(userEssenceFred.getUserEssenceId()).getRequestedFriendsFrom().size(), is(1));
        assertThat(friendController.postFriendRequest(userEssenceJohn.getUserEssenceId(), this.authentication).getStatusCode(), is(HttpStatus.BAD_REQUEST));

    }

}