package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.service.StandardDrinkService;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class StandardDrinkController {

    private final StandardDrinkService standardDrinkService;
    private final UserService userService;

    @GetMapping("/standard-drinks")
    public String showStandardDrinksPage(Model model) {
        return "standard_drinks";
    }

    @PostMapping("/saveStandardDrinkData")
    public String saveStandardDrinkData(
                                        @ModelAttribute("user") User user,
                                        @RequestParam double standardDrinkPerBottle,
                                        @RequestParam double dailyBottles,
                                        @RequestParam double weeklyBottles,
                                        @RequestParam double monthlyBottles) {
        double dailyDrinks = Math.round(dailyBottles * standardDrinkPerBottle * 100.0) / 100.0;
        double weeklyDrinks = Math.round(weeklyBottles * standardDrinkPerBottle * 100.0) / 100.0;
        double monthlyDrinks = Math.round(monthlyBottles * standardDrinkPerBottle * 100.0) / 100.0;

        standardDrinkService.saveStandardDrinkData(user, dailyDrinks, weeklyDrinks, monthlyDrinks);
        return "redirect:/generate-plan"; // Redirect to dashboard or any other appropriate page
    }
}
