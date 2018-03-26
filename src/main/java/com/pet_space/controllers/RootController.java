package com.pet_space.controllers;

import com.pet_space.models.essences.RoleEssence;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = "root")
@Controller
public class RootController {
    private static final Logger LOG = getLogger(RootController.class);
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public RootController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRootView(Authentication auth, HttpSession session) {
        session.setAttribute("users", this.userEssenceRepository.findAll());
        return "redirect:/root/" + auth.getName();
    }

    @RequestMapping(value = {"{nickname}", ""}, method = RequestMethod.PUT)
    public ResponseEntity putChangeRoleUserEssence(@RequestParam(name = "userId") UUID userId, RoleEssence roleEssence) {
        UserEssence user = this.userEssenceRepository.findOne(userId);
        this.userEssenceRepository.save(user.setRole(roleEssence));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
