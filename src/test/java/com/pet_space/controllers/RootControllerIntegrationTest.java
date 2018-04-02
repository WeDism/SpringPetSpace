package com.pet_space.controllers;

import org.junit.Test;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RootControllerIntegrationTest extends ControllerInit {
    @Test
    public void getRootView() {
        when(this.authentication.getName()).thenReturn(USER_ESSENCE_FRED.getNickname());
        new RootController(this.userEssenceRepository, this.customUserEssenceRepository).getRootView(this.authentication, this.mockHttpSession);
        assertNotNull(this.mockHttpSession.getValue("users"));
        assertThat(this.mockHttpSession.getValue("users"), is(this.userEssenceRepository.findAll()));
    }
}
