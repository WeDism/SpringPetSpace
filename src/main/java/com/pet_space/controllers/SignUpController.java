package com.pet_space.controllers;

import com.pet_space.models.essences.RoleEssence;
import com.pet_space.models.essences.StatusEssence;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.USER;
import static com.pet_space.models.essences.StatusEssence.StatusEssenceEnum.ACTIVE;
import static com.pet_space.models.essences.StatusEssence.StatusEssenceEnum.INACTIVE;
import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class SignUpController {
    private static final Logger LOG = getLogger(SignUpController.class);
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public SignUpController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "sign_up", method = RequestMethod.POST)
    public String postUserEssence(@ModelAttribute("userEssence") @Valid UserEssence userEssence, BindingResult result, Model model) {
        if (!result.hasErrors() && !this.userEssenceRepository.existsByNickname(userEssence.getNickname())
                && !this.userEssenceRepository.existsByEmail(userEssence.getEmail())) {

            if (userEssence.getRole().equals(RoleEssence.of(USER)))
                userEssence.setStatusEssence(StatusEssence.of(ACTIVE));
            else userEssence.setStatusEssence(StatusEssence.of(INACTIVE));

            this.userEssenceRepository.save(userEssence);
            model.addAttribute("stateRegistration", Boolean.TRUE);
        } else model.addAttribute("stateRegistration", Boolean.FALSE);
        return "login";
    }
}
