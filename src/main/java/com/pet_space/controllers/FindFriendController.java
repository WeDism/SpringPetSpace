package com.pet_space.controllers;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.services.CustomUserEssenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping(value = {"/admin/find_friend", "/user/find_friend"})
@Controller
public class FindFriendController {

    private final CustomUserEssenceRepository customUserEssenceRepository;

    @Autowired
    public FindFriendController(CustomUserEssenceRepository customUserEssenceRepository) {
        this.customUserEssenceRepository = customUserEssenceRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected String findFriendView() {
        return "findFriends";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String findFriends(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "surname") String surname,
                                 @RequestParam(name = "patronymic") String patronymic, Model model, HttpSession session) {
        List<UserEssence> userEssences
                = customUserEssenceRepository.fiendFriend(((UserEssence) session.getAttribute("user")), name, surname, patronymic);
        model.addAttribute("friends", userEssences);
        return "findFriends";
    }
}
