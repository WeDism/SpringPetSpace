package com.pet_space.controllers;

import com.pet_space.repositories.DbInit;
import org.junit.Ignore;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
}
