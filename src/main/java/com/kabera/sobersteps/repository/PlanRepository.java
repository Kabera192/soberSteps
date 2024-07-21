package com.kabera.sobersteps.repository;

import com.kabera.sobersteps.model.Plan;
import com.kabera.sobersteps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findByUser(User user);
}
