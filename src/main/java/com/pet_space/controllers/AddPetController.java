package com.pet_space.controllers;

import com.pet_space.models.Pet;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.GenusPetRepository;
import com.pet_space.repositories.PetRepository;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.USER;
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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public String postUserPet(@ModelAttribute("pet") @Valid Pet pet, BindingResult result, Authentication authentication, Model model) {
        model.addAttribute("genusPet", this.genusPetRepository.findAll());

        if (result.hasErrors()) return "addPet";

        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        if (this.petRepository.findByNameAndOwner(pet.getName(), user) != null) {
            model.addAttribute("genusPetName", String.format("This is %s name you already use", pet.getName()));
            return "addPet";
        }

        pet.setOwner(user);
        this.petRepository.save(pet);
        model.addAttribute(USER.name().toLowerCase(), this.userEssenceRepository.findOne(user.getUserEssenceId()));
        model.addAttribute("petIsAdded", true);
        return "addPet";
    }
}
