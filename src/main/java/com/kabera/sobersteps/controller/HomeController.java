package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.dto.RegisterUserDto;
import com.kabera.sobersteps.dto.ResponseDto;
import com.kabera.sobersteps.dto.SurveyAnswersDto;
import com.kabera.sobersteps.model.Question;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.service.CommunityService;
import com.kabera.sobersteps.service.PlanService;
import com.kabera.sobersteps.service.SurveyService;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class HomeController {
    private final UserService userService;

    private final CommunityService communityService;

    private final PlanService planService;

    private final SurveyService surveyService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/create-account")
    public String createAccount(Model model){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        model.addAttribute("user", registerUserDto);
        return "signUp";
    }

    @GetMapping("/sober-steps/login")
    public String login(){
        return "login";
    }

    @GetMapping("/survey")
    public String survey(Model model, @ModelAttribute User user){

        List<Question> questions = surveyService.getSurveyQuestions();
        model.addAttribute("questions", questions);

        model.addAttribute("surveyResponse", new ResponseDto());
        return "survey";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        User user = userService.findUserByEmail(userService.getUsername()).orElseThrow(null);
        model.addAttribute("username", userService.getUserProfile().getUsername());
        model.addAttribute("email", userService.getUserProfile().getEmail());
        model.addAttribute("communities", communityService.getAllCommunities());
        model.addAttribute("myCommunities", communityService.getCommunitiesByUser(user));
        model.addAttribute("allCommunities", communityService.getNonMemberCommunities(user));
        model.addAttribute("plan", planService.getPlanByUser(user));
        return "dashboard";
    }

    @GetMapping("/plans")
    public String plans(){
        return "plans";
    }

    @GetMapping("/create-account/hp")
    public String createAccountHp(Model model){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        model.addAttribute("user", registerUserDto);
        return "hp_signUp";
    }

    @GetMapping("/create-account/moh")
    public String createAccountMoH(Model model){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        model.addAttribute("user", registerUserDto);
        return "moh_signUp";
    }

    @GetMapping("/hp/dashboard")
    public String HpDashboard(Model model){
        model.addAttribute("surveys", surveyService.findAllSurveys());
        return "hp_dashboard";
    }
}
