package com.kabera.sobersteps.impl;

import com.kabera.sobersteps.model.ChatMessage;
import com.kabera.sobersteps.model.Community;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.ChatMessageRepository;
import com.kabera.sobersteps.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public void saveMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }

    @Override
    public List<ChatMessage> getCommunityMessages(Community community) {
        return chatMessageRepository.findByCommunity(community);
    }

    @Override
    public List<ChatMessage> getPersonalMessage(User user) {
        return chatMessageRepository.findBySender(user);
    }

    @Override
    public List<ChatMessage> getMessagesForUser(Long userId) {
        return chatMessageRepository.findByTargetTypeAndTargetId("user", userId);
    }

    @Override
    public List<ChatMessage> getMessagesForCommunity(Long communityId) {
        return chatMessageRepository.findByTargetTypeAndTargetId("community", communityId);
    }
}
