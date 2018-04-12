package com.pet_space.repositories;

import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.messages.MessageOfUserId;
import com.pet_space.models.messages.MessageState;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageOfUserRepository extends CrudRepository<MessageOfUser, MessageOfUserId> {
    @Transactional
    @Modifying
    @Query("UPDATE MessageOfUser mou SET mou.state = :messageState WHERE mou.primaryKey = :messageOfUserId")
    int update(@Param("messageOfUserId") MessageOfUserId messageOfUserId, @Param("messageState") MessageState messageState);
}
