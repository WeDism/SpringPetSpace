package com.pet_space.controllers.essences;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = "user")
@Controller
public class UserController {
    private static final Logger LOG = getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String getUserView(Authentication authentication) {
        return "redirect:/user/" + authentication.getName();
    }

}
