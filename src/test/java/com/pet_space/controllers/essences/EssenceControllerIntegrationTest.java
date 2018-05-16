package com.pet_space.controllers.essences;

import com.pet_space.controllers.ControllerInit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EssenceControllerIntegrationTest extends ControllerInit {

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(USER_ESSENCE_FRED);
    }

    @Test
    public void getUserNicknameView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profile/" + USER_ESSENCE_FRED.getNickname().toLowerCase())
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);

        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(model().attribute("user", USER_ESSENCE_FRED))
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    public void getError404View() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profile/new_nickname")
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);

        resultNew.andExpect(status().isNotFound())
                .andExpect(view().name("errors/error404"));
    }

    @Test
    public void getProfileView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/profile/" + USER_ESSENCE_JOHN.getNickname().toLowerCase())
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);

        resultNew.andExpect(status().isAccepted())
                .andExpect(view().name("essence"))
                .andExpect(model().attribute("foundEssence", USER_ESSENCE_JOHN));
    }
}