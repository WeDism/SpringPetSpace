package com.pet_space.controllers.messages;

import com.pet_space.helpers.MessageHelper;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageState;
import com.pet_space.models.messages.MessageState.MessageStateEnum;
import com.pet_space.repositories.MessageOfUserRepository;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = {"user/messages/new", "admin/messages/new", "root/messages/new"})
@RestController
public class NewMessageRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);
    private final UserEssenceRepository userEssenceRepository;
    private final MessageOfUserRepository messageOfUserRepository;

    @Autowired
    public NewMessageRestController(UserEssenceRepository userEssenceRepository, MessageOfUserRepository messageOfUserRepository) {
        this.userEssenceRepository = userEssenceRepository;
        this.messageOfUserRepository = messageOfUserRepository;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getNewMessages(Authentication authentication) {
        UserEssence user = this.userEssenceRepository.findByNicknameWithEagerMessagesToAndFrom(authentication.getName());
        return MessageHelper.getNewMessages(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity putStateMessage(Authentication authentication, @RequestParam("messageId") UUID messageId, @RequestParam("state") MessageStateEnum messageStateEnum) {
        UserEssence user = this.userEssenceRepository.findByNicknameWithEagerMessagesToAndFrom(authentication.getName());
        Optional<MessageOfUser> message = user.getMessagesTo().stream()
                .filter(m -> m.getMessage().getMessageId().equals(messageId)).findFirst();
        if (message.isPresent()) {
            MessageOfUser messageOfUser = message.get().setState(MessageState.of(messageStateEnum));
            this.messageOfUserRepository.save(messageOfUser);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
