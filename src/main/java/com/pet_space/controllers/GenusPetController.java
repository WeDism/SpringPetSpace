package com.pet_space.controllers;

import com.pet_space.models.GenusPet;
import com.pet_space.repositories.GenusPetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public String getGenusPetView(Model model) {
        model.addAttribute("genusPetSet", this.genusPetRepository.findAll());
        return "addGenusPet";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public String postNewGenusPet(@ModelAttribute("genusPet") @Valid GenusPet genusPet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genusPetSet", this.genusPetRepository.findAll());
            return "addGenusPet";
        }

        Optional<GenusPet> genusPetOptional = Optional.ofNullable(this.genusPetRepository.findOne(genusPet.getName()));
        if (!genusPetOptional.isPresent()) {
            this.genusPetRepository.save(genusPet);
            model.addAttribute("genusPetIsAdded", true);
        } else model.addAttribute("genusPetIsAdded", false);
        model.addAttribute("genusPetSet", this.genusPetRepository.findAll());
        return "addGenusPet";
    }
}
