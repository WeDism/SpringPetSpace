package com.pet_space.controllers;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = "admin")
@Controller
public class AdminController {
    private static final Logger LOG = getLogger(AdminController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String getAdminView(Authentication auth) {
        return "redirect:/root/" + auth.getName();
    }

    @RequestMapping(value = "{nickname}", method = RequestMethod.GET)
    public String getAdminView() {
        return "admin";
    }
}
