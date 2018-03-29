package com.pet_space.controllers;

import com.pet_space.models.essences.FriendId;
import com.pet_space.models.essences.Friends;
import com.pet_space.models.essences.StateFriend;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.FriendsRepository;
import com.pet_space.repositories.UserEssenceRepository;
import com.pet_space.services.CustomUserEssenceRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.USER;
import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = {"admin", "user", "root"})
@Controller
public class FriendController {
    private static final Logger LOG = getLogger(FriendController.class);
    private final CustomUserEssenceRepository customUserEssenceRepository;
    private final FriendsRepository friendsRepository;
    private final UserEssenceRepository userEssenceRepository;

    @Autowired
    public FriendController(CustomUserEssenceRepository customUserEssenceRepository, FriendsRepository friendsRepository, UserEssenceRepository userEssenceRepository) {
        this.customUserEssenceRepository = customUserEssenceRepository;
        this.friendsRepository = friendsRepository;
        this.userEssenceRepository = userEssenceRepository;
    }

    @RequestMapping(value = "{nickname}", method = RequestMethod.GET)
    public String getUserNicknameView(Authentication authentication, HttpSession session, Model model) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        session.setAttribute("role", user.getRole().getRoleEssenceEnum().name().toLowerCase());
        model.addAttribute(USER.name().toLowerCase(), user);
        return user.getRole().toString().toLowerCase();
    }

    @RequestMapping(value = "find_friend", method = RequestMethod.GET)
    public String getFindFriendView() {
        return "findFriends";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "find_friend", method = RequestMethod.POST)
    public String postFindFriends(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "surname") String surname,
                                  @RequestParam(name = "patronymic") String patronymic, Model model, Authentication authentication) {
        List<UserEssence> userEssences = customUserEssenceRepository.fiendFriend(
                this.userEssenceRepository.findByNickname(authentication.getName()), name, surname, patronymic);
        model.addAttribute("friends", userEssences);
        return "findFriends";
    }

    @RequestMapping(value = "friend_request", method = RequestMethod.POST)
    public ResponseEntity postFriendRequest(@RequestParam(name = "user_essence_id") UUID userEssenceId, Authentication authentication) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        UserEssence friend = this.userEssenceRepository.findOne(userEssenceId);
        FriendId friendId = new FriendId(user, friend);
        StateFriend state = new StateFriend(StateFriend.StateFriendEnum.REQUESTED);
        Friends friends = this.friendsRepository.findOne(friendId);
        if (friends == null) {
            friends = new Friends(friendId, state);
            this.friendsRepository.save(friends);
            return new ResponseEntity(HttpStatus.CREATED);
        } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "friend_request", method = RequestMethod.DELETE)
    public ResponseEntity deleteFriendRequest(@RequestParam(name = "user_essence_id") UUID userEssenceId, Authentication authentication) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        UserEssence friend = this.userEssenceRepository.findOne(userEssenceId);
        FriendId friendId = new FriendId(user, friend);
        Friends friends = this.friendsRepository.findOne(friendId);
        if (friends != null) {
            this.friendsRepository.delete(friends.getPrimaryKey());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "friend_request", method = RequestMethod.PUT)
    public ResponseEntity putStateFriendRequest(@RequestParam(name = "user_essence_id") UUID userEssenceId,
                                                Authentication authentication, @RequestParam(name = "state_friend") StateFriend.StateFriendEnum stateFriendEnum) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        UserEssence friend = this.userEssenceRepository.findOne(userEssenceId);
        FriendId friendId = new FriendId(friend, user);
        Friends friends = this.friendsRepository.findOne(friendId);
        if (friends != null) {
            this.friendsRepository.save(friends.setState(new StateFriend(stateFriendEnum)));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
