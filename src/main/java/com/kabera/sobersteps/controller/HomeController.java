package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.dto.RegisterUserDto;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

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
    public String survey(){
        return "survey";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("username", userService.getUserProfile().getUsername());
        model.addAttribute("email", userService.getUserProfile().getEmail());
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
    public String HpDashboard(){
        return "hp_dashboard";
    }
}
