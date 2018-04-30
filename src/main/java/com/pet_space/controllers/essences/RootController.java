package com.pet_space.controllers.essences;

import com.pet_space.custom_repositories.CustomUserEssenceRepository;
import com.pet_space.models.essences.RoleEssence;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = "root")
@Controller
public class RootController {
    private static final Logger LOG = getLogger(RootController.class);
    private final UserEssenceRepository userEssenceRepository;
    private final CustomUserEssenceRepository customUserEssenceRepository;

    @Autowired
    public RootController(UserEssenceRepository userEssenceRepository, CustomUserEssenceRepository customUserEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
        this.customUserEssenceRepository = customUserEssenceRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRootView(Authentication auth, HttpSession session) {
        session.setAttribute("users", this.userEssenceRepository.findAll());
        return "redirect:/root/" + auth.getName();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = {"{nickname}", ""}, method = RequestMethod.PUT)
    public void putChangeRoleUserEssence(@RequestParam(name = "userId") UUID userId, @ModelAttribute("roleEssence") RoleEssence roleEssence) {
        UserEssence user = this.userEssenceRepository.findOne(userId);
        this.userEssenceRepository.save(user.setRole(roleEssence));
    }

    @RequestMapping(value = "profile/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserEssence(@PathVariable("id") UUID essenceId, Authentication authentication, HttpSession session) {
        UserEssence userEssence = this.userEssenceRepository.findByNickname(authentication.getName());
        if (!userEssence.getUserEssenceId().equals(essenceId)) {
            this.customUserEssenceRepository.deleteCascadeById(essenceId);
            session.setAttribute("users", this.userEssenceRepository.findAll());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
