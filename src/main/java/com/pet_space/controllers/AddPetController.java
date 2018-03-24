package com.pet_space.controllers;

import com.pet_space.models.Pet;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.GenusPetRepository;
import com.pet_space.repositories.PetRepository;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = {"user/add_pet", "admin/add_pet"})
@Controller
public class AddPetController {
    private static final Logger LOG = getLogger(AddPetController.class);
    private final PetRepository petRepository;
    private final GenusPetRepository genusPetRepository;
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public AddPetController(PetRepository petRepository, GenusPetRepository genusPetRepository, UserEssenceRepository userEssenceRepository) {
        this.petRepository = petRepository;
        this.genusPetRepository = genusPetRepository;
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAddPetView(Model model) {
        model.addAttribute("genusPet", this.genusPetRepository.findAll());
        return "addPet";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postUserPet(@ModelAttribute Pet pet, HttpSession session) {
        pet.setPetId(UUID.randomUUID());
        this.petRepository.save(pet);
        UserEssence user = (UserEssence) session.getAttribute("user");
        session.setAttribute("user", this.userEssenceRepository.findOne(user.getUserEssenceId()));
        return user.getRole().toString().toLowerCase() + "/add_pet";
    }
}
