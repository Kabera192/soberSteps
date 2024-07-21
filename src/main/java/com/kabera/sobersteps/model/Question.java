package com.kabera.sobersteps.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @OneToMany(mappedBy = "surveyQuestion", cascade = CascadeType.ALL)
    private List<Answers> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
