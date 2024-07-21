package com.kabera.sobersteps.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String responseTxt;

    private Integer weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question surveyQuestion;
}
