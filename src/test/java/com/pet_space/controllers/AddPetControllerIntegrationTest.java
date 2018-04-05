package com.pet_space.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.UUID;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.USER;
import static com.pet_space.repositories.PetRepositoryTestData.PET_LAYMA;
import static com.pet_space.repositories.PetRepositoryTestData.PET_TIMON;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AddPetControllerIntegrationTest extends ControllerInit {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .dispatchOptions(true).build();

        ResultActions auth = null;
        try {
            auth = this.mockMvc.perform(post("/login")
                    .param("nickname", USER_ESSENCE_JOHN.getNickname()).param("password", USER_ESSENCE_JOHN.getPassword()));
            auth.andExpect(redirectedUrl("/pet_space"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        MvcResult result = auth.andReturn();
        this.session = (MockHttpSession) result.getRequest().getSession();
    }

    @Test
    public void postUserPetSuccess() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/admin/add_pet")
                .params(new LinkedMultiValueMap<String, String>() {{
                    put("name", Arrays.asList(PET_LAYMA.getName()));
                    put("weight", Arrays.asList(PET_LAYMA.getWeight().toString()));
                    put("genusPet", Arrays.asList(PET_LAYMA.getGenusPet().getName()));
                }}).session(this.session);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isCreated())
                .andExpect(model().attribute("petIsAdded", true))
                .andExpect(model().attribute(USER.name().toLowerCase(), USER_ESSENCE_JOHN))
                .andExpect(model().attribute("genusPet", this.genusPetRepository.findAll()));
    }

    @Test
    public void postUserPetCheckPetNameConstraint() throws Exception {
        MockHttpServletRequestBuilder requestBuilderFirst = post("/admin/add_pet")
                .params(new LinkedMultiValueMap<String, String>() {{
                    put("name", Arrays.asList(PET_LAYMA.getName()));
                    put("weight", Arrays.asList(PET_LAYMA.getWeight().toString()));
                    put("genusPet", Arrays.asList(PET_LAYMA.getGenusPet().getName()));
                }}).session(this.session);

        MockHttpServletRequestBuilder requestBuilderSecond = post("/admin/add_pet")
                .params(new LinkedMultiValueMap<String, String>() {{
                    put("name", Arrays.asList(PET_LAYMA.getName()));
                    put("weight", Arrays.asList(PET_TIMON.getWeight().toString()));
                    put("genusPet", Arrays.asList(PET_TIMON.getGenusPet().getName()));
                }}).session(this.session);

        ResultActions resultFirst = this.mockMvc.perform(requestBuilderFirst);
        ResultActions resultSecond = this.mockMvc.perform(requestBuilderFirst);

        resultSecond.andExpect(status().isBadRequest())
                .andExpect(model().attribute("genusPetName", String.format("This is %s name you already use", PET_LAYMA.getName())))
                .andExpect(model().attribute("genusPet", this.genusPetRepository.findAll()));

    }

    @Test
    public void postUserPetBindingResultHasErrors() throws Exception {
        MockHttpServletRequestBuilder requestBuilderFirst = post("/admin/add_pet")
                .params(new LinkedMultiValueMap<String, String>() {{
                    put("petId", Arrays.asList(UUID.randomUUID().toString()));
                    put("name", Arrays.asList("p"));
                    put("weight", Arrays.asList(PET_LAYMA.getWeight().toString()));
                    put("genusPet", Arrays.asList(PET_LAYMA.getGenusPet().getName()));
                }}).session(this.session);

        ResultActions resultNew = this.mockMvc.perform(requestBuilderFirst);

        resultNew.andExpect(status().isBadRequest())
                .andExpect(model().hasErrors())
                .andExpect(model().attribute("genusPet", this.genusPetRepository.findAll()));

    }
}