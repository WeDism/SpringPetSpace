package com.pet_space.controllers;

import com.pet_space.repositories.*;
import com.pet_space.services.CustomUserEssenceRepository;
import org.junit.After;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;

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
