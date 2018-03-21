package com.pet_space.controllers;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.UserEssenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PetSpaceController {

    final UserEssenceRepository userEssenceRepository;

    @Autowired
    public PetSpaceController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(value = "pet_space", method = RequestMethod.GET)
    public String role(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEssence userEssence = this.userEssenceRepository.findByNickname(auth.getName());
        model.addAttribute("user", userEssence);
        return "redirect:/" + userEssence.getRole().getRoleEssenceEnum().name().toLowerCase();
    }
}
