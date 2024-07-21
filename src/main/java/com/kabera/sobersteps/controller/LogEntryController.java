package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.UserRepository;
import com.kabera.sobersteps.service.LogEntryService;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LogEntryController {

    private final LogEntryService logEntryService;

    private final UserService userService;

    @PostMapping("/submitLog")
    public String saveLog(
                @RequestParam String alcoholFreeDays,
                @RequestParam String alcoholLimit,
                @RequestParam String healthyActivities) {

        User user = userService.findUserByEmail(userService.getUsername()).orElseThrow(null);
        logEntryService.saveLogEntry(user, "AlcoholFreeDays", alcoholFreeDays);
        logEntryService.saveLogEntry(user, "AlcoholLimit", alcoholLimit);
        logEntryService.saveLogEntry(user, "HealthyActivities", healthyActivities);
        return "redirect:/dashboard";
    }
}
