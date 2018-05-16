package com.pet_space.controllers.pets;

import com.pet_space.controllers.ControllerInit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;

import static com.pet_space.repositories.PetRepositoryTestData.PET_LAYMA;
import static com.pet_space.repositories.PetRepositoryTestData.PET_TIMON;
import static com.pet_space.repositories.UserEssenceRepositoryTestData.USER_ESSENCE_JOHN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class AddPetControllerIntegrationTest extends ControllerInit {

    @Override
    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(USER_ESSENCE_JOHN);
    }

    @Test
    public void postUserPetSuccess() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/admin/add_pet")
                .params(new LinkedMultiValueMap<String, String>(3) {{
                    add("name", PET_LAYMA.getName());
                    add("weight", PET_LAYMA.getWeight().toString());
                    add("genusPet", PET_LAYMA.getGenusPet().getName());
                }}).session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().isCreated())
                .andExpect(model().attribute("petIsAdded", true))
                .andExpect(model().attribute("user", USER_ESSENCE_JOHN))
                .andExpect(model().attribute("genusPet", this.genusPetRepository.findAll()));
    }

    @Test
    public void postUserPetCheckPetNameConstraint() throws Exception {
        MockHttpServletRequestBuilder requestBuilderFirst = post("/admin/add_pet")
                .params(new LinkedMultiValueMap<String, String>(3) {{
                    add("name", PET_LAYMA.getName());
                    add("weight", PET_LAYMA.getWeight().toString());
                    add("genusPet", PET_LAYMA.getGenusPet().getName());
                }}).session(this.mockHttpSession);

        MockHttpServletRequestBuilder requestBuilderSecond = post("/admin/add_pet")
                .params(new LinkedMultiValueMap<String, String>(3) {{
                    add("name", PET_LAYMA.getName());
                    add("weight", PET_TIMON.getWeight().toString());
                    add("genusPet", PET_TIMON.getGenusPet().getName());
                }}).session(this.mockHttpSession);

        ResultActions resultFirst = this.mockMvc.perform(requestBuilderFirst);
        ResultActions resultSecond = this.mockMvc.perform(requestBuilderSecond);

        resultSecond.andExpect(status().isBadRequest())
                .andExpect(model().attribute("genusPetName", String.format("This is %s name you already use", PET_LAYMA.getName())))
                .andExpect(model().attribute("genusPet", this.genusPetRepository.findAll()));

    }

    @Test
    public void postUserPetBindingResultHasErrors() throws Exception {
        MockHttpServletRequestBuilder requestBuilderFirst = post("/admin/add_pet")
                .params(new LinkedMultiValueMap<String, String>(3) {{
                    add("name", "p");
                    add("weight", PET_LAYMA.getWeight().toString());
                    add("genusPet", PET_LAYMA.getGenusPet().getName());
                }}).session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilderFirst);

        resultNew.andExpect(status().isBadRequest())
                .andExpect(model().hasErrors())
                .andExpect(model().attribute("genusPet", this.genusPetRepository.findAll()));

    }

    @Test
    public void getAddPetView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin/add_pet")
                .session(this.mockHttpSession);

        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(model().attribute("user", USER_ESSENCE_JOHN))
                .andExpect(model().attribute("genusPet", this.genusPetRepository.findAll()))
                .andExpect(view().name("addPet"));
    }
}