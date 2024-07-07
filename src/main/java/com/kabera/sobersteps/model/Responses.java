package com.kabera.sobersteps.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Responses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String responseTxt;
    private Integer weight;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question surveyQuestion;
}
