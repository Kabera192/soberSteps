package com.kabera.sobersteps.service;

import com.kabera.sobersteps.model.Community;
import com.kabera.sobersteps.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommunityService {
    Community createCommunity(String name);

    List<Community> getAllCommunities();

    Community findByCommunityName(String name);

    List<Community> getCommunitiesByUser(User user);

    List<Community> getNonMemberCommunities(User user);

    Community findById(Long communityId);
}
