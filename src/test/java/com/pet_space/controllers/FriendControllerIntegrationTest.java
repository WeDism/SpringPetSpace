package com.pet_space.controllers;

import com.pet_space.models.Pet;
import com.pet_space.models.essences.UserEssence;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

import java.util.Collections;
import java.util.Set;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.USER;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FriendControllerIntegrationTest extends ControllerInit {

    @Test
    public void postFriendRequest() {
        MockHttpSession session = new MockHttpSession();
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

        session.setAttribute(USER.name().toLowerCase(), userEssenceFred);
        assertThat(friendController.postFriendRequest(userEssenceJohn.getUserEssenceId(), session).getStatusCode(), is(HttpStatus.OK));
        assertThat(((UserEssence) session.getAttribute(USER.name().toLowerCase())).getRequestedFriendsFrom().size(), is(1));
    }

}