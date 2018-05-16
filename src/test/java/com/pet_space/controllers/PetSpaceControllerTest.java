package com.pet_space.controllers;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.pet_space.controllers.ControllerInitTestData.PET_SPACE_PATH;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PetSpaceControllerTest extends ControllerInit {

    @Test
    public void getRoleAsUser() throws Exception {
        this.initMockSession(USER_ESSENCE_FRED);
        MockHttpServletRequestBuilder requestBuilder = get(PET_SPACE_PATH)
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(String.format("/%s", USER_ESSENCE_FRED.getRole().toString().toLowerCase())));
    }

    @Test
    public void getRoleAsAdmin() throws Exception {
        this.initMockSession(USER_ESSENCE_JOHN);
        MockHttpServletRequestBuilder requestBuilder = get(PET_SPACE_PATH)
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(String.format("/%s", USER_ESSENCE_JOHN.getRole().toString().toLowerCase())));
    }

    @Test
    public void getRoleAsRoot() throws Exception {
        this.initMockSession(USER_ESSENCE_BART);
        MockHttpServletRequestBuilder requestBuilder = get(PET_SPACE_PATH)
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(String.format("/%s", USER_ESSENCE_BART.getRole().toString().toLowerCase())));
    }
}