package com.pet_space.controllers.pets;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.pets.Pet;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public String getAddPetView(Authentication authentication, Model model) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("genusPet", this.genusPetRepository.findAll());
        return "addPet";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postUserPet(@ModelAttribute("pet") @Valid Pet pet, BindingResult result, Authentication authentication) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        ModelAndView modelAndView = new ModelAndView("addPet");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);

        if (!result.hasErrors()) {
            if (this.petRepository.findByNameAndOwner(pet.getName(), user) != null) {
                modelAndView.addObject("genusPetName", String.format("This is %s name you already use", pet.getName()));
            } else {
                pet.setOwner(user);
                this.petRepository.save(pet);
                modelAndView.addObject("petIsAdded", true);
                modelAndView.setStatus(HttpStatus.CREATED);
            }
        }

        modelAndView.addObject("user", user);
        modelAndView.addObject("genusPet", this.genusPetRepository.findAll());
        return modelAndView;
    }
}
