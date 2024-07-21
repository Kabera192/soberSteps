package com.kabera.sobersteps.impl;

import com.kabera.sobersteps.model.Community;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.CommunityRepository;
import com.kabera.sobersteps.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Override
    public Community createCommunity(String name) {
        Community community = Community.builder()
                .name(name)
                .build();
        return communityRepository.save(community);
    }

    @Override
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    @Override
    public Community findByCommunityName(String name) {
        return communityRepository.findByName(name);
    }

    @Override
    public List<Community> getCommunitiesByUser(User user) {
        return user.getCommunities();
    }

    @Override
    public List<Community> getNonMemberCommunities(User user) {
        List<Community> communities = getAllCommunities();
        List<Community> userCommunities = getCommunitiesByUser(user);
        return communities.stream()
                .filter(community -> !userCommunities.contains(community))
                .toList();
    }

    @Override
    public Community findById(Long communityId) {
        return communityRepository.findById(communityId).orElseThrow(null);
    }
}
