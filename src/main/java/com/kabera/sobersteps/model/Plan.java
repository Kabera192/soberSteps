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
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String planDetails;
}