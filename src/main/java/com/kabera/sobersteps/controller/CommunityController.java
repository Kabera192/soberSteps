package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.model.Community;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.UserRepository;
import com.kabera.sobersteps.service.CommunityService;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/communities")
    public String listCommunities(Model model) {
        List<Community> communities = communityService.getAllCommunities();
        model.addAttribute("communities", communities);
        return "community_list";
    }

    @PostMapping("/createCommunity")
    public String createCommunity(@RequestParam String communityName) {
        User user = userService.findUserByEmail(userService.getUsername()).orElseThrow(null);
        Community community = communityService.createCommunity(communityName);
        user.getCommunities().add(community);
        userRepository.save(user);

        return "redirect:/dashboard";
    }

    @PostMapping("/joinCommunity")
    public String joinCommunity(@RequestParam Long communityId) {
        User user = userService.findUserByEmail(userService.getUsername()).orElseThrow(null);
        Community community = communityService.findById(communityId);
        user.getCommunities().add(community);
        userRepository.save(user);

        return "redirect:/dashboard";
    }
}
