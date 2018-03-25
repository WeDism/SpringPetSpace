package com.pet_space.controllers;

import com.pet_space.repositories.DbInit;
import org.junit.Ignore;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;

@Ignore
public class ControllerInit extends DbInit{

    @Autowired
    protected FriendController friendController;
    @Autowired
    protected MockHttpSession session;

    @Override
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
    }
}
