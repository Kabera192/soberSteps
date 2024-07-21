package com.kabera.sobersteps.service;

import com.kabera.sobersteps.model.StandardDrinkData;
import com.kabera.sobersteps.model.User;
import org.springframework.stereotype.Service;

@Service
public interface StandardDrinkService {

    StandardDrinkData getStandardDrinkData(User user);

    void saveStandardDrinkData(User user, double dailyDrinks, double weeklyDrinks, double monthlyDriniks);
}
