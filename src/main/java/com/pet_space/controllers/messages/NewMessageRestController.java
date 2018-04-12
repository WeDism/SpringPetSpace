package com.pet_space.controllers.messages;

import com.pet_space.helpers.MessageHelper;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = {"user/message/new", "admin/message/new", "root/message/new"})
@RestController
public class NewMessageRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public NewMessageRestController(UserEssenceRepository userEssenceRepository) {
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getNewMessages(Authentication authentication) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());

        return MessageHelper.getNewMessages(user);
    }

}
