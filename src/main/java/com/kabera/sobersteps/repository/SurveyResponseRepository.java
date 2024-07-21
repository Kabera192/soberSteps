package com.kabera.sobersteps.repository;

import com.kabera.sobersteps.model.SurveyResponse;
import com.kabera.sobersteps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
    SurveyResponse findByUser(User user);
}
