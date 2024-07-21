package com.kabera.sobersteps.service;

import com.kabera.sobersteps.dto.ResponseDto;
import com.kabera.sobersteps.dto.SurveyDto;
import com.kabera.sobersteps.dto.SurveyResponseDto;
import com.kabera.sobersteps.model.Question;
import com.kabera.sobersteps.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {
    void createSurvey(SurveyDto surveyDto);

    List<SurveyResponseDto> findAllSurveys();

    List<Question> getSurveyQuestions();

    void saveSurveyResponse(ResponseDto responseDto, User user);

    int calculateResponseWeight(User user);
}
