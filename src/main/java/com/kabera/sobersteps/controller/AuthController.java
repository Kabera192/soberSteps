package com.kabera.sobersteps.controller;

import com.kabera.sobersteps.dto.RegisterUserDto;
import com.kabera.sobersteps.model.RoleName;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class AuthController {

    private final UserService userService;


    @PostMapping("/create-account")
    public String register(
            @ModelAttribute("user") RegisterUserDto registerUserDto,
            BindingResult bindingResult,
            Model model) {

        // Here we are checking if the user already exists in the system
        if(userService.findUserByEmail(registerUserDto.getEmail()).isPresent()){
            bindingResult.rejectValue("email", null,
                    "Account already exists.");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", registerUserDto);
            return "signUp";
        }

        User user = userService.registerUser(registerUserDto, RoleName.ROLE_QU);
        model.addAttribute("user", user);

        return "redirect:/survey-intro";
    }

    @PostMapping("/create-account/hp")
    public String registerHp(
            @ModelAttribute("user") RegisterUserDto registerUserDto,
            BindingResult bindingResult,
            Model model) {



        // Here we are checking if the user already exists in the system
        if(userService.findUserByEmail(registerUserDto.getEmail()).isPresent()){
            bindingResult.rejectValue("email", null,
                    "Account already exists.");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", registerUserDto);
            return "hp_signUp";
        }

        userService.registerUser(registerUserDto, RoleName.ROLE_HP);


        return "redirect:/login?success";
    }

    @PostMapping("/create-account/moh")
    public String registerMoH(
            @ModelAttribute("user") RegisterUserDto registerUserDto,
            BindingResult bindingResult,
            Model model) {

        // Here we are checking if the user already exists in the system
        if(userService.findUserByEmail(registerUserDto.getEmail()).isPresent()){
            bindingResult.rejectValue("email", null,
                    "Account already exists.");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", registerUserDto);
            return "moh_signUp";
        }

        userService.registerUser(registerUserDto, RoleName.ROLE_MOH);


        return "redirect:/login?success";
    }
}