package com.pet_space.jstl_tags.functions;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageOfUser;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class OwnersToStringFunction {
    private static final Logger LOG = getLogger(OwnersToStringFunction.class);

    public static String ownersToString(Message message, UserEssence userEssence, boolean messageFromCurrentUser) {
        StringBuilder stringBuilder = new StringBuilder();
        String variableString = messageFromCurrentUser ? "" : "and ";
        message.getOwners().stream()
                .map(MessageOfUser::getUserEssence)
                .filter(e -> !e.equals(userEssence))
                .forEach(ue -> stringBuilder.append(ue.getNickname()).append(','));
        String result = stringBuilder.length() > 0 ? stringBuilder.deleteCharAt(stringBuilder.length() - 1).insert(0, variableString).toString() : null;
        return result;
    }

}
