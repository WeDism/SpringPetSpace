package com.pet_space.controllers.pets;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.pets.GenusPet;
import com.pet_space.repositories.GenusPetRepository;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Optional;

@RequestMapping(value = {"admin/add_genus_pet", "root/add_genus_pet"})
@Controller
public class GenusPetController {
    private static final Logger LOG = LoggerFactory.getLogger(GenusPetController.class);
    private final GenusPetRepository genusPetRepository;
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public GenusPetController(GenusPetRepository genusPetRepository, UserEssenceRepository userEssenceRepository) {
        this.genusPetRepository = genusPetRepository;
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGenusPetView(Authentication authentication, Model model) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("genusPetSet", this.genusPetRepository.findAll());
        return "addGenusPet";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public String postNewGenusPet(@ModelAttribute("genusPet") @Valid GenusPet genusPet, BindingResult result, Authentication authentication, Model model) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());

        if (!result.hasErrors()) {
            Optional<GenusPet> genusPetOptional = Optional.ofNullable(this.genusPetRepository.findOne(genusPet.getName()));
            if (!genusPetOptional.isPresent()) {
                this.genusPetRepository.save(genusPet);
                model.addAttribute("genusPetIsAdded", true);
            } else model.addAttribute("genusPetIsAdded", false);
        }

        model.addAttribute("user", user);
        model.addAttribute("genusPetSet", this.genusPetRepository.findAll());
        return "addGenusPet";
    }
}
