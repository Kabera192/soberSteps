package com.kabera.sobersteps.impl;

import com.kabera.sobersteps.dto.*;
import com.kabera.sobersteps.model.*;
import com.kabera.sobersteps.repository.AnswerRepository;
import com.kabera.sobersteps.repository.QuestionRepository;
import com.kabera.sobersteps.repository.SurveyRepository;
import com.kabera.sobersteps.repository.SurveyResponseRepository;
import com.kabera.sobersteps.service.SurveyService;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final SurveyRepository surveyRepository;

    private final SurveyResponseRepository surveyResponseRepository;

    @Override
    public void createSurvey(SurveyDto surveyDto) {
        Survey survey = new Survey();
        survey.setSurveyName(surveyDto.getSurveyName());
        survey.setDateCreated(LocalDate.now());
        surveyRepository.save(survey);

        if(surveyDto.getQuestions() != null){
            for(QuestionDto questionDto : surveyDto.getQuestions()){
                Question question = new Question();
                question.setQuestionText(questionDto.getText());
                question.setSurvey(survey);
                questionRepository.save(question);

                if(questionDto.getAnswers() != null){
                    for(AnswerDto answerDto : questionDto.getAnswers()){
                        Answers answer = new Answers();
                        answer.setResponseTxt(answerDto.getText());
                        answer.setWeight(answerDto.getWeight());
                        answer.setSurveyQuestion(question);
                        answerRepository.save(answer);
                    }
                }
            }
        }
    }

    @Override
    public List<SurveyResponseDto> findAllSurveys() {
        return surveyRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private SurveyResponseDto mapToDto(Survey survey) {
        return SurveyResponseDto.builder()
                .surveyName(survey.getSurveyName())
                .dateCreated(survey.getDateCreated())
                .responses(survey.getResponses().size())
                .build();
    }

    @Override
    public List<Question> getSurveyQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void saveSurveyResponse(ResponseDto responseDto, User user) {
        SurveyResponse surveyResponse = new SurveyResponse();

        surveyResponse.setAnswers(getAnswersList(responseDto));

        surveyResponse.setSurvey(surveyRepository.findBySurveyName("New User Audit Survey"));
        surveyResponse.setUser(user);
        surveyResponseRepository.save(surveyResponse);
    }

    @Override
    public int calculateResponseWeight(User user) {
        SurveyResponse responses = surveyResponseRepository.findByUser(user);
        return responses.getAnswers().stream().mapToInt(Answers::getWeight).sum();
    }

    private List<Answers> getAnswersList(ResponseDto responseDto) {
        return responseDto.getAnswersList().stream()
                .map(this::mapToAnswerList)
                .toList();
    }

    private Answers mapToAnswerList(SurveyAnswersDto surveyAnswersDto){
        Answers answer = answerRepository.findById(surveyAnswersDto.getAnswerId())
                .orElseThrow(null);
        System.out.println(answer);
        return answer;
    }
}
