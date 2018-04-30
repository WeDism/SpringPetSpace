package com.pet_space.controllers.messages;

import com.pet_space.custom_repositories.CustomMessageRepository;
import com.pet_space.helpers.FriendHelper;
import com.pet_space.helpers.MessageHelper;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageState;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Set;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.VIEWED;

@RequestMapping(value = {"user/messages", "admin/messages", "root/messages"})
@Controller
public class MessageController {
    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);
    private final UserEssenceRepository userEssenceRepository;
    private final CustomMessageRepository customMessageRepository;

    @Autowired
    public MessageController(UserEssenceRepository userEssenceRepository, CustomMessageRepository customMessageRepository) {
        this.userEssenceRepository = userEssenceRepository;
        this.customMessageRepository = customMessageRepository;
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.GET)
    public String getMessagesView(Authentication authentication, Model model) {
        UserEssence user = this.customMessageRepository.updateAllMessagesOfUserEssence
                (new UserEssence().setNickname(authentication.getName()), MessageState.of(VIEWED));
        Set<UserEssence> friends = FriendHelper.getApprovedFriends(user);

        model.addAttribute("user", user);
        model.addAttribute("friends", friends);
        model.addAttribute("messages", MessageHelper.getAllSortedMessages(user));
        return "message";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> postMessage(@Valid @RequestBody Message message, BindingResult result, Authentication authentication) {
        if (result.hasErrors() || message.getOwners().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        message.setAuthor(user).setDate(LocalDateTime.now());

        this.customMessageRepository.save(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
