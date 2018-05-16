package com.pet_space.controllers.essences;

import com.pet_space.controllers.ControllerInit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminControllerIntegrationTest extends ControllerInit {

    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(USER_ESSENCE_JOHN);
    }

    @Test
    public void getAdminView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin")
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/" + USER_ESSENCE_JOHN.getNickname().toLowerCase()));
    }

    @Test
    public void getAdminNicknameView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin/" + USER_ESSENCE_JOHN.getNickname().toLowerCase())
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("user", USER_ESSENCE_JOHN))
                .andExpect(view().name("admin"));
    }

    @Test
    public void getRedirectProfileView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin/" + USER_ESSENCE_FRED.getNickname().toLowerCase())
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/" + USER_ESSENCE_FRED.getNickname().toLowerCase()));
    }
}