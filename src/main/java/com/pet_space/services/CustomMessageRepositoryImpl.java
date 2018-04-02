package com.pet_space.services;

import com.pet_space.models.Message;
import com.pet_space.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository
public class CustomMessageRepositoryImpl implements CustomMessageRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CustomMessageRepositoryImpl.class);

    final MessageRepository messageRepository;

    @Autowired
    public CustomMessageRepositoryImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void save(Message message) {
        Message _message = new Message()
                .setDate(message.getDate())
                .setText(message.getText());

        this.messageRepository.save(_message);

        _message.setAuthor(message.getAuthor())
                .setOwners(message.getOwners());

        this.messageRepository.save(_message);
    }

    @Override
    public void deleteAll() {
        Iterable<Message> all = this.messageRepository.findAll();
        all.forEach(m -> m.setOwners(null));
        this.messageRepository.delete(all);
    }
}
