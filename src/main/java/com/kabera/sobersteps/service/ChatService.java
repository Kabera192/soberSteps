package com.kabera.sobersteps.service;

import com.kabera.sobersteps.model.ChatMessage;
import com.kabera.sobersteps.model.Community;
import com.kabera.sobersteps.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    void saveMessage(ChatMessage message);

    List<ChatMessage> getCommunityMessages(Community community);

    List<ChatMessage> getPersonalMessage(User user);

    List<ChatMessage> getMessagesForUser(Long userId);

    List<ChatMessage> getMessagesForCommunity(Long communityId);
}
