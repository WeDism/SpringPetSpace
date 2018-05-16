package com.pet_space.controllers.essences;

import com.pet_space.controllers.ControllerInit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerIntegrationTest extends ControllerInit {
    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(USER_ESSENCE_FRED);
    }

    @Test
    public void getUserView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/user")
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/" + USER_ESSENCE_FRED.getNickname().toLowerCase()));
    }

    @Test
    public void getUserNicknameView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/user/" + USER_ESSENCE_FRED.getNickname().toLowerCase())
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("user", USER_ESSENCE_FRED))
                .andExpect(view().name("user"));
    }

    @Test
    public void getRedirectProfileView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/user/" + USER_ESSENCE_JOHN.getNickname().toLowerCase())
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/" + USER_ESSENCE_JOHN.getNickname().toLowerCase()));
    }
}