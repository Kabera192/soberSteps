package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.dto.ChatMessageDto;
import com.kabera.sobersteps.model.ChatMessage;
import com.kabera.sobersteps.model.Community;
import com.kabera.sobersteps.model.MessageType;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.UserRepository;
import com.kabera.sobersteps.service.ChatService;
import com.kabera.sobersteps.service.CommunityService;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final CommunityService communityService;

    private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/chat")
    public String chat(Model model) {
        User user = userService.findUserByEmail(userService.getUsername()).orElseThrow(null);

        List<Community> communities = communityService.getCommunitiesByUser(user);
        List<User> users = userRepository.findAll().stream()
                .filter(u -> !u.equals(user))
                .toList();

        model.addAttribute("communities", communities);
        model.addAttribute("users", users);
        model.addAttribute("loggedInUser", user);

        return "chat";
    }

    @GetMapping("/chat/messages")
    public String getMessages(@RequestParam String targetType, @RequestParam Long targetId, Model model) {
        List<ChatMessage> messages;

        if ("user".equals(targetType)) {
            messages = chatService.getMessagesForUser(targetId);
        } else if ("community".equals(targetType)) {
            messages = chatService.getMessagesForCommunity(targetId);
        } else {
            throw new IllegalArgumentException("Invalid target type");
        }

        model.addAttribute("messages", messages);
        return "message_list";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessageDto chatMessageDto){
        ChatMessage chatMessage = ChatMessage.builder()
                .content(chatMessageDto.getContent())
                .type(MessageType.valueOf(chatMessageDto.getType()))
                .sender(userRepository.findByUsername(chatMessageDto.getSender()))
                .targetId(chatMessageDto.getTargetId())
                .targetType(chatMessageDto.getTargetType())
                .build();
        chatService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        // Here we are connection the new user to the websocket session.
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender().getUsername());
        chatService.saveMessage(chatMessage);
        return chatMessage;
    }
}
