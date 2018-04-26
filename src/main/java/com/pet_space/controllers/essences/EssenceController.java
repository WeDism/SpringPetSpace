package com.pet_space.controllers.essences;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class EssenceController {
    private static final Logger LOG = getLogger(EssenceController.class);
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public EssenceController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(value = "profile/{nickname}", method = RequestMethod.GET)
    public ModelAndView getUserNicknameView(@PathVariable("nickname") String nickname, Authentication authentication) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        if (user.getNickname().equals(nickname)) {
            return new ModelAndView("redirect:/" + user.getRole().toString().toLowerCase());
        }

        UserEssence userEssence = this.userEssenceRepository.findByNickname(nickname);
        if (userEssence != null) {
            ModelAndView modelAndView = new ModelAndView("essence");
            modelAndView.addObject("foundEssence", userEssence);
            return modelAndView;
        } else return new ModelAndView("errors/error404");
    }

}
