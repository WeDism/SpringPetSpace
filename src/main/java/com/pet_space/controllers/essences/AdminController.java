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

@RequestMapping(value = "admin")
@Controller
public class AdminController {
    private static final Logger LOG = getLogger(AdminController.class);
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public AdminController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAdminView(Authentication authentication) {
        return "redirect:/admin/" + authentication.getName();
    }

    @RequestMapping(value = "{nickname}", method = RequestMethod.GET)
    public String getAdminNicknameView(Authentication authentication, Model model, @PathVariable("nickname") String nickname) {
        return PathHelper.getPath(authentication, nickname, () -> {
            UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
            model.addAttribute("user", user);
            return user;
        });
    }

}
