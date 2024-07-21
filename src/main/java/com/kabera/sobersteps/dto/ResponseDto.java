package com.kabera.sobersteps.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    private List<SurveyAnswersDto> answersList = new ArrayList<>();

    public void addSurveyAnswerDto(SurveyAnswersDto surveyAnswersDto){
        this.answersList.add(surveyAnswersDto);
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "answersList=" + answersList.toString() +
                '}';
    }
}
