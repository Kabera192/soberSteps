package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.dto.ResponseDto;
import com.kabera.sobersteps.dto.SurveyDto;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/create-survey")
    public String createSurvey(Model model){
        model.addAttribute("surveyDto", new SurveyDto());
        return "create-survey";
    }

    @PostMapping("/create-survey")
    public String saveSurvey(@ModelAttribute SurveyDto surveyDto){
        surveyService.createSurvey(surveyDto);
        return "redirect:/hp/dashboard";
    }

    @PostMapping("/submit-survey")
    public String submitSurvey(@ModelAttribute("surveyResponse") ResponseDto responseDto, @ModelAttribute User user){
        surveyService.saveSurveyResponse(responseDto, user);
        return "redirect:/standard-drinks";
    }

    @GetMapping("/survey-intro")
    public String surveyIntro(Model model, @ModelAttribute("user") User user) {
        if(user.getId() == null){
            return "redirect:/sober-steps/login";
        }
        return "survey_intro";
    }
}
