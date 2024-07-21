package com.kabera.sobersteps.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="tbl_plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    private double alcoholFreeDays;

    private double standardDrinksLimit;

    private int planDuration;

    private String healthyActivities;

    private int activityDuration;
}