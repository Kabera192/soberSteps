package com.kabera.sobersteps.impl;

import com.kabera.sobersteps.model.Plan;
import com.kabera.sobersteps.model.StandardDrinkData;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.PlanRepository;
import com.kabera.sobersteps.service.PlanService;
import com.kabera.sobersteps.service.StandardDrinkService;
import com.kabera.sobersteps.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {


    private final SurveyService surveyService;

    private final StandardDrinkService standardDrinkService;

    private final PlanRepository planRepository;

    public Plan generatePlan(User user) {
        int surveyWeight = surveyService.calculateResponseWeight(user);
        StandardDrinkData drinkData = standardDrinkService.getStandardDrinkData(user);

        // Calculate plan details
        double dailyDrinks = drinkData.getDailyDrinks();
        double weeklyDrinks = drinkData.getWeeklyDrinks();
        double monthlyDrinks = drinkData.getMonthlyDrinks();

        // Fraction to reduce based on survey weight
        double reductionFraction = calculateReductionFraction(surveyWeight);

        Plan plan = new Plan();
        plan.setUser(user);
        plan.setAlcoholFreeDays(calculateAlcoholFreeDays(reductionFraction));
        plan.setStandardDrinksLimit(dailyDrinks - (dailyDrinks * reductionFraction));
        plan.setPlanDuration(calculatePlanDuration(reductionFraction));
        plan.setHealthyActivities(calculateHealthyActivities(reductionFraction));
        plan.setActivityDuration(calculateActivityDuration(reductionFraction));

        // Store the plan
        return planRepository.save(plan);
    }

    @Override
    public Plan getPlanByUser(User user) {
        return planRepository.findByUser(user);
    }

    private int calculateActivityDuration(double reductionFraction) {
        return (int) (30 + (10 * reductionFraction));
    }

    private String calculateHealthyActivities(double reductionFraction) {
        if (reductionFraction <= 0.1) {
            return "Walking, Meditation";
        } else if (reductionFraction <= 0.2) {
            return "Running, Yoga";
        } else {
            return "Cycling, Gym";
        }
    }

    private int calculatePlanDuration(double reductionFraction) {
        return (int) (30 + (30 * reductionFraction));
    }

    private double calculateAlcoholFreeDays(double reductionFraction) {
        return (2 + (2 * reductionFraction));
    }

    private double calculateReductionFraction(int surveyWeight) {
        // Example logic: higher weight means less aggressive plan
        if (surveyWeight <= 25 && surveyWeight >= 13) {
            return 0.1;
        } else if (surveyWeight <= 12 && surveyWeight >= 8) {
            return 0.2;
        } else {
            return 0.3;
        }
    }
}
