package com.pet_space.controllers.pets;

import com.pet_space.controllers.ControllerInit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GenusPetControllerIntegrationTest extends ControllerInit {

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(USER_ESSENCE_JOHN);
    }

    @Test
    public void getGenusPetView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin/add_genus_pet")
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isOk())
                .andExpect(model().attribute("user", USER_ESSENCE_JOHN))
                .andExpect(model().attribute("genusPetSet", this.genusPetRepository.findAll()));
    }

    @Test
    public void postNewGenusPet() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/admin/add_genus_pet")
                .param("genusPet", "wolf")
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isCreated())
                .andExpect(model().attribute("user", USER_ESSENCE_JOHN))
                .andExpect(model().attribute("genusPetSet", this.genusPetRepository.findAll()))
                .andExpect(model().attribute("genusPetIsAdded", true));

        resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isCreated())
                .andExpect(model().attribute("user", USER_ESSENCE_JOHN))
                .andExpect(model().attribute("genusPetSet", this.genusPetRepository.findAll()))
                .andExpect(model().attribute("genusPetIsAdded", false));

    }
}