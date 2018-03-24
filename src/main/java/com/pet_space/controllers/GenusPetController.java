package com.pet_space.controllers;

import com.pet_space.models.GenusPet;
import com.pet_space.repositories.GenusPetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequestMapping(value = {"admin/add_genus_pet", "root/add_genus_pet"})
@Controller
public class GenusPetController {
    private static final Logger LOG = LoggerFactory.getLogger(GenusPetController.class);
    private final GenusPetRepository genusPetRepository;

    @Autowired
    public GenusPetController(GenusPetRepository genusPetRepository) {
        this.genusPetRepository = genusPetRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGenusPetView(HttpSession session, Model model) {
        session.removeAttribute("genusPetIsAdded");
        model.addAttribute("genusPetSet", this.genusPetRepository.findAll());
        return "addGenusPet";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postNewGenusPet(@RequestParam("name") GenusPet genusPet, HttpSession session, Model model) {
        Optional<GenusPet> genusPetOptional = Optional.ofNullable(this.genusPetRepository.findOne(genusPet.getName()));
        if (!genusPetOptional.isPresent()) {
            this.genusPetRepository.save(genusPet);
            session.setAttribute("genusPetIsAdded",true);
        }
        else
            session.setAttribute("genusPetIsAdded", false);
        model.addAttribute("genusPetSet", this.genusPetRepository.findAll());
        return "addGenusPet";
    }
}
