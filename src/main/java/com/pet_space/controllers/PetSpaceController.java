package com.pet_space.controllers;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;


@Controller
public class PetSpaceController {
    private static final Logger LOG = getLogger(PetSpaceController.class);
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public PetSpaceController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(value = "pet_space", method = RequestMethod.GET)
    public String getRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEssence userEssence = this.userEssenceRepository.findByNickname(authentication.getName());
        return "redirect:/" + userEssence.getRole().getRoleEssenceEnum().name().toLowerCase();
    }
}
