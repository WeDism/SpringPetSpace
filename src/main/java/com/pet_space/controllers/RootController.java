package com.pet_space.controllers;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = "root")
@Controller
public class RootController {
    private static final Logger LOG = getLogger(RootController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String getRootView(Authentication auth) {
        return "redirect:/root/" + auth.getName();
    }

    @RequestMapping(value = "{nickname}", method = RequestMethod.GET)
    public String getRootView() {
        return "root";
    }
}
