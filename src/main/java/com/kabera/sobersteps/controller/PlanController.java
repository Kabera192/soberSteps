package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.model.Plan;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class PlanController {

    private final PlanService planService;

    @GetMapping("/generate-plan")
    public String generatePlan(@ModelAttribute("user") User user, Model model) {

        Plan plan = planService.generatePlan(user);
        model.addAttribute("plan", plan);
        return "redirect:/dashboard";
    }
}
