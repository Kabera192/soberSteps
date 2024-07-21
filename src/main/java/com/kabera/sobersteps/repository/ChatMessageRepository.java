package com.kabera.sobersteps.repository;

import com.kabera.sobersteps.model.ChatMessage;
import com.kabera.sobersteps.model.Community;
import com.kabera.sobersteps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByCommunity(Community community);

    List<ChatMessage> findBySender(User user);

    List<ChatMessage> findByTargetTypeAndTargetId(String targetType, Long targetId);
}
