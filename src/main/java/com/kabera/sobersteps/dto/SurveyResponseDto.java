package com.kabera.sobersteps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyResponseDto {

    private String surveyName;

    private LocalDate dateCreated;

    private Integer responses;
}
