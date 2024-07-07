package com.kabera.sobersteps.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_survey_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(name = "tbl_surveyresponses_questions")
    private List<Question> surveyQuestions = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tbl_surveyresponses_responses")
    private List<Responses> responses = new ArrayList<>();
}
