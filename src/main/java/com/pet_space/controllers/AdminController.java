package com.pet_space.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "admin")
@Controller
public class AdminController {
    @RequestMapping(value = "/{nickname}", method = RequestMethod.GET)
    public String userView() {
        return "admin";
    }
}
