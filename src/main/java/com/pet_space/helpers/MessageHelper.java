package com.pet_space.helpers;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageState;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.NEW;
import static com.pet_space.models.messages.MessageState.MessageStateEnum.VIEWED;

public final class MessageHelper {
    private MessageHelper() {
    }

    public static List<Message> getNewMessages(UserEssence user) {
        return MessageHelper.getSortedMessagesWithMessageState(user.getMessagesTo(), MessageState.of(NEW));
    }

    public static List<Message> getViewedMessages(UserEssence user) {
        return MessageHelper.getSortedMessagesWithMessageState(user.getMessagesTo(), MessageState.of(VIEWED));
    }

    public static List<Message> getAllSortedMessages(UserEssence user) {
        List<Message> messages = new ArrayList<>();
        messages.addAll(user.getMessagesTo().stream()
                .map(MessageOfUser::getMessage)
                .collect(Collectors.toList()));

        messages.addAll(user.getMessagesFrom());
        return messages.stream()
                .sorted(Comparator.comparing(Message::getDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private static List<Message> getSortedMessagesWithMessageState(Set<MessageOfUser> messages, MessageState messageState) {
        return messages.stream()
                .filter(m -> m.getState().equals(messageState))
                .map(MessageOfUser::getMessage)
                .sorted(Comparator.comparing(Message::getDate))
                .collect(Collectors.toList());
    }

}
