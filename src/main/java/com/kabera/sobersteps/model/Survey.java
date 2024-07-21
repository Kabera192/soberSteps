package com.kabera.sobersteps.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surveyName;

    private LocalDate dateCreated;

    @OneToMany(mappedBy = "survey")
    private List<SurveyResponse> responses = new ArrayList<>();

    @OneToMany(mappedBy = "survey")
    private List<Question> questions = new ArrayList<>();
}
