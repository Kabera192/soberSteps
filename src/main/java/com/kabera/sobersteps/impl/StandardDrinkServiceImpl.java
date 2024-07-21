package com.kabera.sobersteps.impl;

import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.StandardDrinkRepository;
import com.kabera.sobersteps.service.StandardDrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kabera.sobersteps.model.StandardDrinkData;

@Service
@RequiredArgsConstructor
public class StandardDrinkServiceImpl implements StandardDrinkService {

    private final StandardDrinkRepository standardDrinkRepository;

    @Override
    public StandardDrinkData getStandardDrinkData(User user) {
        return standardDrinkRepository.findByUser(user);
    }

    @Override
    public void saveStandardDrinkData(User user, double dailyDrinks, double weeklyDrinks, double monthlyDrinks) {
        StandardDrinkData data = new StandardDrinkData();
        data.setUser(user);
        data.setDailyDrinks(dailyDrinks);
        data.setWeeklyDrinks(weeklyDrinks);
        data.setMonthlyDrinks(monthlyDrinks);
        standardDrinkRepository.save(data);
    }
}
