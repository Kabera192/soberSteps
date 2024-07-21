package com.kabera.sobersteps.repository;

import com.kabera.sobersteps.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    // TODO - find a way to show all communities a user is a part of.

    Community findByName(String communityName);
}
