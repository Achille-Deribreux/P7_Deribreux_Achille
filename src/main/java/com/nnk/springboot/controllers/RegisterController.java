package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.Service.UserService;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping
    public String showRegisterForm(Model model) {
        return "/registerForm";
    }

    @PostMapping
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if(userService.uniqueUsernameValidator(user.getUsername())) {
                if(userService.validatePassword(user.getPassword())){
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    user.setPassword(encoder.encode(user.getPassword()));
                    userService.add(user);
                    return "redirect:/login?success";
                }
                else{
                    model.addAttribute("passwordError","Invalid password, it must have uppercase letters, digits and special character");
                }
            }
            else{
                model.addAttribute("userError","Username already known, please log in");
            }
        }
        return "/registerForm";
    }
}
