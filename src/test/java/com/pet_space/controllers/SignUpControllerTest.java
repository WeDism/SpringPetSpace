package com.pet_space.controllers;

import com.pet_space.models.essences.StatusEssence;
import com.pet_space.models.essences.UserEssence;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;

import static com.pet_space.models.essences.StatusEssence.StatusEssenceEnum.ACTIVE;
import static com.pet_space.models.essences.StatusEssence.StatusEssenceEnum.INACTIVE;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SignUpControllerTest extends ControllerInit {

    @Test
    public void postValidUserEssenceWithRoleUser() throws Exception {
        UserEssence userEssenceFredClone = SerializationUtils.clone(USER_ESSENCE_FRED)
                .setNickname("FRED_CLONE")
                .setEmail("fr-clone@fr-clone.com");
        MultiValueMap<String, String> valueMap = this.getValueMap(userEssenceFredClone);

        MockHttpServletRequestBuilder requestBuilder = post("/sign_up")
                .params(valueMap);
        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("stateRegistration", Boolean.TRUE));

        UserEssence userEssence = this.userEssenceRepository.findByNickname(userEssenceFredClone.getNickname());
        Assert.assertThat(userEssence.getNickname(), is(userEssenceFredClone.getNickname()));
        Assert.assertThat(userEssence.getStatusEssence(), is(StatusEssence.of(ACTIVE)));
    }

    @Test
    public void postValidUserEssenceWithRoleAdmin() throws Exception {
        UserEssence userEssenceJohnClone = SerializationUtils.clone(USER_ESSENCE_JOHN)
                .setNickname("JOHN_CLONE")
                .setEmail("jo-clone@jo-clone.com");
        MultiValueMap<String, String> valueMap = this.getValueMap(userEssenceJohnClone);

        MockHttpServletRequestBuilder requestBuilder = post("/sign_up")
                .params(valueMap);
        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("stateRegistration", Boolean.TRUE));

        UserEssence userEssence = this.userEssenceRepository.findByNickname(userEssenceJohnClone.getNickname());
        Assert.assertThat(userEssence.getNickname(), is(userEssenceJohnClone.getNickname()));
        Assert.assertThat(userEssence.getStatusEssence(), is(StatusEssence.of(INACTIVE)));
    }

    @Test
    public void postUserEssenceWithErrors() throws Exception {
        UserEssence userEssenceFredClone = SerializationUtils.clone(USER_ESSENCE_FRED)
                .setNickname("FRED_CLONE")
                .setEmail("fr-clone@fr-clone.com");
        MultiValueMap<String, String> valueMap = this.getValueMap(userEssenceFredClone);

        MockHttpServletRequestBuilder requestBuilder = post("/sign_up")
                .params(valueMap);
        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("stateRegistration", Boolean.TRUE));

        userEssenceFredClone = SerializationUtils.clone(USER_ESSENCE_FRED)
                .setNickname("FRED_CLONE")
                .setName("N")
                .setEmail("fr-clone@fr-clone.com");
        valueMap = this.getValueMap(userEssenceFredClone);

        requestBuilder = post("/sign_up")
                .params(valueMap);
        resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("stateRegistration", Boolean.FALSE))
                .andExpect(flash().attribute("errors", new ArrayList<ImmutablePair>(3){{
                    add(ImmutablePair.of("Name",  "Name must be between 2 and 200 characters long."));
                    add(ImmutablePair.of("Nickname", "This nickname already used"));
                    add(ImmutablePair.of("Email", "This email already used"));
                }}));
    }

    @NotNull
    private LinkedMultiValueMap<String, String> getValueMap(UserEssence userEssence) {
        return new LinkedMultiValueMap<String, String>(7) {{
            add("email", userEssence.getEmail());
            add("nickname", userEssence.getNickname());
            add("name", userEssence.getName());
            add("password", userEssence.getPassword());
            add("surname", userEssence.getSurname());
            add("birthday", userEssence.getBirthday() != null ? userEssence.getBirthday().toString() : "");
            add("role.roleEssenceEnum", userEssence.getRole().toString());
        }};
    }
}