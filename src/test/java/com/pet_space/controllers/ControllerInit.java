package com.pet_space.controllers;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.DbInit;
import org.junit.Ignore;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.pet_space.controllers.ControllerInitTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@Ignore
public class ControllerInit extends DbInit {
    @Autowired
    protected MockHttpSession mockHttpSession;
    @Mock
    protected Authentication authentication;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    @Override
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .dispatchOptions(true).build();
    }

    @SuppressWarnings("NullableProblems")
    protected void initMockSession(UserEssence userEssence) {
        ResultActions auth = null;
        try {
            auth = this.mockMvc.perform(post(LOGIN_PATH)
                    .param(NICKNAME, userEssence.getNickname())
                    .param(PASSWORD, userEssence.getPassword()));
            auth.andExpect(redirectedUrl(PET_SPACE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("ConstantConditions")
        MvcResult result = auth.andReturn();
        this.mockHttpSession = (MockHttpSession) result.getRequest().getSession();
    }
}
