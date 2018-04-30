package com.pet_space.controllers;

import com.pet_space.models.essences.UserEssence;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SignUpControllerTest extends ControllerInit {

    @Test
    public void postUserEssence() throws Exception {
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        UserEssence userEssenceFredClone = SerializationUtils.clone(USER_ESSENCE_FRED).setNickname("FRED_CLONE").setEmail("fr-clone@fr-clone.com");
        valueMap.add("email", userEssenceFredClone.getEmail());
        valueMap.add("nickname", userEssenceFredClone.getNickname());
        valueMap.add("name", userEssenceFredClone.getName());
        valueMap.add("password", userEssenceFredClone.getPassword());
        valueMap.add("surname", userEssenceFredClone.getSurname());
        valueMap.add("birthday", userEssenceFredClone.getBirthday().toString());
        valueMap.add("role.roleEssenceEnum", userEssenceFredClone.getRole().toString());

        MockHttpServletRequestBuilder requestBuilder = post("/sign_up")
                .params(valueMap);
        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isCreated());
        Assert.assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_FRED.getUserEssenceId()), is(userEssenceFredClone));

    }
}