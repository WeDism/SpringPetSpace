package com.pet_space.controllers.essences;

import com.pet_space.controllers.ControllerInit;
import com.pet_space.models.essences.RoleEssence;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.ADMIN;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RootControllerIntegrationTest extends ControllerInit {
    @Autowired
    private RootController rootController;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        when(this.authentication.getName()).thenReturn(USER_ESSENCE_BART.getNickname());

       this.initMockSession(USER_ESSENCE_BART);
    }

    @Test
    public void getRootView() throws Exception {
        assertThat(this.rootController.getRootView(this.authentication), is("redirect:/root/" + this.authentication.getName()));
        MockHttpServletRequestBuilder requestBuilder =
                get("/root/" + this.authentication.getName()).session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(model().attribute("users", is(this.userEssenceRepository.findAll())))
                .andExpect(model().attribute("user", is(this.userEssenceRepository.findByNickname(this.authentication.getName()))));
    }

    @Test
    public void putChangeRoleUserEssence() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = put("/root")
                .param("userId", USER_ESSENCE_SIMON.getUserEssenceId().toString())
                .param("roleEssenceEnum", ADMIN.name())
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isAccepted());

        Assert.assertThat(this.userEssenceRepository.findOne(USER_ESSENCE_SIMON.getUserEssenceId()).getRole(), CoreMatchers.is(RoleEssence.of(ADMIN)));
    }

    @Test
    public void deleteUserEssence() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/root/profile/" + USER_ESSENCE_FRED.getUserEssenceId())
                .session(this.mockHttpSession);
        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isAccepted());

        Assert.assertNull(this.userEssenceRepository.findOne(USER_ESSENCE_FRED.getUserEssenceId()));


        requestBuilder = delete("/root/profile/" + USER_ESSENCE_BART.getUserEssenceId()).session(this.mockHttpSession);
        resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isBadRequest());

        Assert.assertNotNull(this.userEssenceRepository.findOne(USER_ESSENCE_BART.getUserEssenceId()));
    }
}
