package com.pet_space.controllers.essences;

import com.pet_space.controllers.ControllerInit;
import com.pet_space.models.essences.FriendId;
import com.pet_space.models.essences.Friends;
import com.pet_space.models.essences.StateFriend;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.pets.Pet;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.validator.constraints.Email;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static com.pet_space.models.essences.StateFriend.StateFriendEnum.*;
import static com.pet_space.models.essences.StateFriend.StateFriendEnum.APPROVED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_SIMON;
import static java.util.Arrays.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FriendControllerIntegrationTest extends ControllerInit {
    private UserEssence userEssenceJohn;
    private UserEssence userEssenceFred;
    private UserEssence userEssenceSimon;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.userEssenceJohn = cloneUserEssence(USER_ESSENCE_JOHN, "JOHN_CLONE", "John Clone", "jo-clone@jo-clone.com");
        this.userEssenceFred = cloneUserEssence(USER_ESSENCE_FRED, "JOHN_FRED", "Fred Clone", "fr-clone@fr-clone.com");
        this.userEssenceSimon = cloneUserEssence(USER_ESSENCE_SIMON, "SIMON_CLONE", "SIMON Clone", "si-clone@si-clone.com");

        super.initMockSession(this.userEssenceJohn);
    }

    private UserEssence cloneUserEssence(UserEssence userEssence, String nickname, String name, String email) {
        UserEssence userEssenceClone = SerializationUtils.clone(userEssence)
                .setNickname(nickname)
                .setName(name)
                .setEmail(email);
        Set<Pet> petsFred = userEssenceClone.getPets();
        userEssenceClone.setPets(Collections.emptySet());
        this.userEssenceRepository.save(userEssenceClone.setUserEssenceId(null));
        petsFred.forEach(e -> {
            e.setPetId(null);
            e.setOwner(userEssenceClone);
        });
        this.petRepository.save(petsFred);
        userEssenceClone.setPets(petsFred);
        return userEssenceClone;
    }

    @Test
    public void getFindFriendView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin/find_friend")
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isOk())
                .andExpect(model().attribute("user", this.userEssenceJohn))
                .andExpect(view().name("findFriends"));
    }

    @Test
    public void postFindFriends() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/admin/find_friend")
                .params(new LinkedMultiValueMap<String, String>(3) {{
                    add("name", userEssenceFred.getName());
                    add("surname", "");
                    add("patronymic", "");
                }}).session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(model().attribute("user", this.userEssenceJohn))
                .andExpect(model().attribute("friends", asList(this.userEssenceRepository.findByNickname(userEssenceFred.getNickname()))))
                .andExpect(model().attribute("userFriends",
                        this.userEssenceRepository.findByNickname(userEssenceJohn.getNickname()).getRequestedFriendsFrom()))
                .andExpect(view().name("findFriends"));
    }


    @Test
    public void postFriendRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/admin/friend_request")
                .param("user_essence_id", this.userEssenceFred.getUserEssenceId().toString())
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isCreated());

        assertThat(this.userEssenceRepository.findOne(this.userEssenceJohn.getUserEssenceId()).getRequestedFriendsFrom().size(), is(1));
        assertThat(this.userEssenceRepository.findOne(this.userEssenceFred.getUserEssenceId()).getRequestedFriendsTo().size(), is(1));

        assertThat(this.userEssenceRepository.findOne(this.userEssenceJohn.getUserEssenceId()).getRequestedFriendsFrom().iterator().next(),
                is(new Friends(FriendId.of(this.userEssenceJohn, this.userEssenceFred), StateFriend.of(REQUESTED))));
        assertThat(this.userEssenceRepository.findOne(this.userEssenceFred.getUserEssenceId()).getRequestedFriendsTo().iterator().next(),
                is(new Friends(FriendId.of(this.userEssenceJohn, this.userEssenceFred), StateFriend.of(REQUESTED))));

        assertThat(this.userEssenceRepository.findOne(this.userEssenceJohn.getUserEssenceId()).getRequestedFriendsFrom().iterator().next().getState(),
                is(StateFriend.of(REQUESTED)));
        assertThat(this.userEssenceRepository.findOne(this.userEssenceFred.getUserEssenceId()).getRequestedFriendsTo().iterator().next().getState(),
                is(StateFriend.of(REQUESTED)));

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isBadRequest());
    }


    @Test
    public void deleteFriendRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/admin/friend_request")
                .param("user_essence_id", this.userEssenceSimon.getUserEssenceId().toString())
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isCreated());

        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().size(), is(1));
        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().iterator().next(),
                is(new Friends(FriendId.of(this.userEssenceJohn, this.userEssenceSimon), StateFriend.of(REQUESTED))));
        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().iterator().next().getState(),
                is(StateFriend.of(REQUESTED)));

        requestBuilder = delete("/admin/friend_request")
                .param("user_essence_id", this.userEssenceSimon.getUserEssenceId().toString())
                .session(this.mockHttpSession);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isAccepted());

        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().size(), is(0));

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isBadRequest());
    }

    @Test
    public void putStateFriendRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/admin/friend_request")
                .param("user_essence_id", this.userEssenceSimon.getUserEssenceId().toString())
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isCreated());

        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().size(), is(1));
        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().iterator().next(),
                is(new Friends(FriendId.of(this.userEssenceJohn, this.userEssenceSimon), StateFriend.of(REQUESTED))));
        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().iterator().next().getState(),
                is(StateFriend.of(REQUESTED)));

        requestBuilder = put("/admin/friend_request")
                .param("user_essence_id", this.userEssenceSimon.getUserEssenceId().toString())
                .param("state_friend", APPROVED.toString())
                .session(this.mockHttpSession);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isAccepted());

        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().size(), is(1));
        assertThat(this.userEssenceRepository.findOne(this.userEssenceSimon.getUserEssenceId()).getRequestedFriendsTo().iterator().next().getState(),
                is(StateFriend.of(APPROVED)));

        requestBuilder = delete("/admin/friend_request")
                .param("user_essence_id", this.userEssenceSimon.getUserEssenceId().toString())
                .session(this.mockHttpSession);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isAccepted());

        requestBuilder = put("/admin/friend_request")
                .param("user_essence_id", this.userEssenceSimon.getUserEssenceId().toString())
                .param("state_friend", APPROVED.toString())
                .session(this.mockHttpSession);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isBadRequest());
    }
}