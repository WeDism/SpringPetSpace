package com.pet_space.controllers.essences;

import com.pet_space.controllers.ControllerInit;
import com.pet_space.models.essences.RoleEssence;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.ADMIN;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RootControllerIntegrationTest extends ControllerInit {
    @Autowired
    private RootController rootController;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        when(this.authentication.getName()).thenReturn(USER_ESSENCE_FRED.getNickname());

        ResultActions auth = null;
        try {
            auth = this.mockMvc.perform(post("/login")
                    .param("nickname", USER_ESSENCE_BART.getNickname()).param("password", USER_ESSENCE_BART.getPassword()));
            auth.andExpect(redirectedUrl("/pet_space"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        MvcResult result = auth.andReturn();
        this.mockHttpSession = (MockHttpSession) result.getRequest().getSession();
    }

    @Test
    public void getRootView() {
        assertThat(this.rootController.getRootView(this.authentication, this.mockHttpSession), is("redirect:/root/" + this.authentication.getName()));
        assertNotNull(this.mockHttpSession.getValue("users"));
        assertThat(this.mockHttpSession.getValue("users"), is(this.userEssenceRepository.findAll()));
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
