package com.kabera.sobersteps.service;

import com.kabera.sobersteps.model.Plan;
import com.kabera.sobersteps.model.User;
import org.springframework.stereotype.Service;

@Service
public interface PlanService {

    Plan generatePlan(User user);

    Plan getPlanByUser(User user);
}
