package com.pet_space.controllers;

import com.pet_space.models.essences.Friends;
import com.pet_space.models.essences.StateFriend;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.USER;

@RequestMapping(value = {"user", "admin", "root"})
@Controller
public class MessageController {
    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public MessageController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public String getMessagesView(Authentication authentication, Model model) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        Set<UserEssence> friends = this.getFriends(user);

        model.addAttribute(USER.name().toLowerCase(), user);
        model.addAttribute("friends", friends);
        return "messages";
    }

    private Set<UserEssence> getFriends(UserEssence user) {
        Set<UserEssence> userEssences = new HashSet<>();
        Predicate<Friends> predicate = e -> e.getState().equals(StateFriend.of(StateFriend.StateFriendEnum.APPROVED));
        userEssences.addAll(user.getRequestedFriendsFrom().stream()
                .filter(predicate).map(Friends::getFriend).collect(Collectors.toSet()));
        userEssences.addAll(user.getRequestedFriendsTo().stream()
                .filter(predicate).map(Friends::getUserEssence).collect(Collectors.toSet()));
        return userEssences;
    }
}
