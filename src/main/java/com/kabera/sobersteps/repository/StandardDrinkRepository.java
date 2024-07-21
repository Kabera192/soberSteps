package com.kabera.sobersteps.repository;

import com.kabera.sobersteps.model.StandardDrinkData;
import com.kabera.sobersteps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardDrinkRepository extends JpaRepository<StandardDrinkData,Long> {
    StandardDrinkData findByUser(User user);
}
