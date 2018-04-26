package com.pet_space.custom_repositories;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageState;

public interface CustomMessageRepository {
    Message save(Message message);

    UserEssence updateAllMessagesOfUserEssence(UserEssence entity, MessageState messageState);

}
