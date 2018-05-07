package com.pet_space.controllers.essences;

import com.pet_space.helpers.PathHelper;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = "user")
@Controller
public class UserController {
    private static final Logger LOG = getLogger(UserController.class);
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public UserController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUserView(Authentication authentication) {
        return "redirect:/user/" + authentication.getName();
    }

    @RequestMapping(value = "{nickname}", method = RequestMethod.GET)
    public String getUserNicknameView(Authentication authentication, Model model, @PathVariable("nickname") String nickname) {
        return PathHelper.getPath(authentication, nickname, () -> {
            UserEssence user = this.userEssenceRepository.findByNickname(nickname);
            model.addAttribute("user", user);
            return user;
        });
    }

}
