package com.kabera.sobersteps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyAnswersDto {

    private Long questionId;

    private Long answerId;

    @Override
    public String toString() {
        return "SurveyAnswersDto{" +
                "questionId=" + questionId +
                ", answerId=" + answerId +
                '}';
    }
}
