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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static com.pet_space.models.essences.RoleEssence.RoleEssenceEnum.USER;
import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = {"admin", "user"})
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

    @RequestMapping(value = "find_friend", method = RequestMethod.GET)
    public String getFindFriendView() {
        return "findFriends";
    }

    @RequestMapping(value = "find_friend", method = RequestMethod.POST)
    public String postFindFriends(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "surname") String surname,
                                  @RequestParam(name = "patronymic") String patronymic, Model model, HttpSession session) {
        List<UserEssence> userEssences
                = customUserEssenceRepository.fiendFriend(((UserEssence) session.getAttribute("user")), name, surname, patronymic);
        model.addAttribute("friends", userEssences);
        return "findFriends";
    }

    @RequestMapping(value = "friend_request", method = RequestMethod.POST)
    public ResponseEntity postFriendRequest(@RequestParam(name = "user_essence_id") UUID userEssenceId, HttpSession session) {
        UserEssence user = (UserEssence) session.getAttribute(USER.name().toLowerCase());
        UserEssence friend = this.userEssenceRepository.findOne(userEssenceId);
        FriendId friendId = new FriendId(user, friend);
        StateFriend state = new StateFriend(StateFriend.StateFriendEnum.REQUESTED);
        Friends friends = this.friendsRepository.findOne(friendId);
        if (friends == null) {
            friends = new Friends(friendId, state);
            this.friendsRepository.save(friends);
            session.setAttribute(USER.name().toLowerCase(), this.userEssenceRepository.findOne(user.getUserEssenceId()));
            return new ResponseEntity(HttpStatus.CREATED);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "friend_request", method = RequestMethod.DELETE)
    public ResponseEntity deleteFriendRequest(@RequestParam(name = "user_essence_id") UUID userEssenceId, HttpSession session) {
        UserEssence user = (UserEssence) session.getAttribute(USER.name().toLowerCase());
        UserEssence friend = this.userEssenceRepository.findOne(userEssenceId);
        FriendId friendId = new FriendId(user, friend);
        Friends friends = this.friendsRepository.findOne(friendId);
        if (friends != null) {
            this.friendsRepository.delete(friends.getPrimaryKey());
            session.setAttribute(USER.name().toLowerCase(), this.userEssenceRepository.findOne(user.getUserEssenceId()));
            return new ResponseEntity(HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "friend_request", method = RequestMethod.PUT)
    public ResponseEntity deleteFriendRequest(@RequestParam(name = "user_essence_id") UUID userEssenceId,
                                              HttpSession session, @RequestParam(name = "state_friend") StateFriend.StateFriendEnum stateFriendEnum) {
        UserEssence user = (UserEssence) session.getAttribute(USER.name().toLowerCase());
        UserEssence friend = this.userEssenceRepository.findOne(userEssenceId);
        FriendId friendId = new FriendId(friend, user);
        Friends friends = this.friendsRepository.findOne(friendId);
        if (friends != null) {
            this.friendsRepository.save(friends.setState(new StateFriend(stateFriendEnum)));
            session.setAttribute(USER.name().toLowerCase(), this.userEssenceRepository.findOne(user.getUserEssenceId()));
            return new ResponseEntity(HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
