package com.kabera.sobersteps.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_standard_drinks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardDrinkData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double dailyDrinks;
    private double weeklyDrinks;
    private double monthlyDrinks;

    // Getters and Setters
}
