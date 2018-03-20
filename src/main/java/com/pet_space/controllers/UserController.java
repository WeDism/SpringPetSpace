package com.pet_space.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "user")
@Controller
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String userView(){
        return "user";
    }
}
