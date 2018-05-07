package com.pet_space.controllers.essences;

import com.pet_space.custom_repositories.CustomUserEssenceRepository;
import com.pet_space.models.essences.FriendId;
import com.pet_space.models.essences.Friends;
import com.pet_space.models.essences.StateFriend;
import com.pet_space.models.essences.UserEssence;
import com.pet_space.repositories.FriendsRepository;
import com.pet_space.repositories.UserEssenceRepository;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.pet_space.models.essences.StateFriend.StateFriendEnum;
import static com.pet_space.models.essences.StateFriend.StateFriendEnum.REQUESTED;
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

    @RequestMapping(value = "find_friend", method = RequestMethod.GET)
    public String getFindFriendView(Authentication authentication, Model model) {
        UserEssence userEssence = this.userEssenceRepository.findByNickname(authentication.getName());
        model.addAttribute("user", userEssence);
        return "findFriends";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "find_friend", method = RequestMethod.POST)
    public String postFindFriends(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "surname") String surname,
                                  @RequestParam(name = "patronymic") String patronymic, Model model, Authentication authentication) {
        UserEssence userEssence = this.userEssenceRepository.findByNickname(authentication.getName());
        List<UserEssence> userEssences = customUserEssenceRepository.fiendFriend(userEssence, name, surname, patronymic);
        model.addAttribute("friends", userEssences);
        model.addAttribute("user", userEssence);
        model.addAttribute("userFriends", userEssence.getRequestedFriendsFrom()
                .stream().map(Friends::getFriend).collect(Collectors.toSet()));
        return "findFriends";
    }

    @RequestMapping(value = "friend_request", method = RequestMethod.POST)
    public ResponseEntity postFriendRequest(@RequestParam(name = "user_essence_id") UUID userEssenceId, Authentication authentication) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        UserEssence friend = this.userEssenceRepository.findOne(userEssenceId);
        FriendId friendId = FriendId.of(user, friend);
        StateFriend state = StateFriend.of(REQUESTED);
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
        FriendId friendId = FriendId.of(user, friend);
        Friends friends = this.friendsRepository.findOne(friendId);
        if (friends != null) {
            this.friendsRepository.delete(friends.getPrimaryKey());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "friend_request", method = RequestMethod.PUT)
    public ResponseEntity putStateFriendRequest(@RequestParam(name = "user_essence_id") UUID userEssenceId, Authentication authentication,
                                                @RequestParam(name = "state_friend") StateFriendEnum stateFriendEnum) {
        UserEssence user = this.userEssenceRepository.findByNickname(authentication.getName());
        UserEssence friend = this.userEssenceRepository.findOne(userEssenceId);
        FriendId friendId = FriendId.of(friend, user);
        Friends friends = this.friendsRepository.findOne(friendId);
        if (friends != null) {
            this.friendsRepository.save(friends.setState(StateFriend.of(stateFriendEnum)));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
