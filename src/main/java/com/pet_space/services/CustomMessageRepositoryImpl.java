package com.pet_space.services;

import com.pet_space.models.essences.UserEssence;
import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageState;
import com.pet_space.repositories.MessageOfUserRepository;
import com.pet_space.repositories.MessageRepository;
import com.pet_space.repositories.UserEssenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.pet_space.models.messages.MessageState.MessageStateEnum.NEW;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository
public class CustomMessageRepositoryImpl implements CustomMessageRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CustomMessageRepositoryImpl.class);
    private final MessageRepository messageRepository;
    private final MessageOfUserRepository messageOfUserRepository;
    private final UserEssenceRepository userEssenceRepository;

    public CustomMessageRepositoryImpl(MessageRepository messageRepository, MessageOfUserRepository messageOfUserRepository, UserEssenceRepository userEssenceRepository) {
        this.messageRepository = messageRepository;
        this.messageOfUserRepository = messageOfUserRepository;
        this.userEssenceRepository = userEssenceRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserEssence updateAllMessagesOfUserEssence(UserEssence entity, MessageState messageState) {
        UserEssence userEssence = entity.getNickname() == null ?
                this.userEssenceRepository.findOne(entity.getUserEssenceId()) :
                this.userEssenceRepository.findByNickname(entity.getNickname());
        userEssence.getMessagesTo().forEach(e -> {
            this.messageOfUserRepository.update(e.getPrimaryKey(), messageState);
            e.setState(messageState);
        });
        return userEssence;
    }

    //TODO check transaction
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Message save(final Message message) {
        message.getOwners().forEach(pk -> pk.getPrimaryKey().setOwner(
                this.userEssenceRepository.findByNickname(pk.getPrimaryKey().getOwner().getNickname())
        ));

        Message messageNew = new Message()
                .setDate(message.getDate())
                .setAuthor(message.getAuthor())
                .setText(message.getText())
                .setMessageId(message.getMessageId());
        this.messageRepository.save(messageNew);

        messageNew.setOwners(message.getOwners()).getOwners().forEach(pk -> {
            pk.getPrimaryKey().setMessage(messageNew);
            pk.setState(MessageState.of(NEW));
        });
        this.messageOfUserRepository.save(messageNew.getOwners());

        return messageNew;
    }
}
