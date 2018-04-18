package com.pet_space.controllers.essences;

import com.pet_space.controllers.ControllerInit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_FRED;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RootControllerIntegrationTest extends ControllerInit {
    @Autowired
    private RootController rootController;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        when(this.authentication.getName()).thenReturn(USER_ESSENCE_FRED.getNickname());
    }

    @Test
    public void getRootView() {
        assertThat(this.rootController.getRootView(this.authentication, this.mockHttpSession), is("redirect:/root/" + this.authentication.getName()));
        assertNotNull(this.mockHttpSession.getValue("users"));
        assertThat(this.mockHttpSession.getValue("users"), is(this.userEssenceRepository.findAll()));
    }
}
