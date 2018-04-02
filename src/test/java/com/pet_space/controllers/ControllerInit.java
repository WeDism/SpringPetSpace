package com.pet_space.controllers;

import com.pet_space.repositories.DbInit;
import org.junit.Ignore;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;

@Ignore
public class ControllerInit extends DbInit{

    @Autowired
    protected FriendController friendController;
    @Autowired
    protected MockHttpSession mockHttpSession;
    @Mock
    protected Authentication authentication;

    @Override
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
    }
}
