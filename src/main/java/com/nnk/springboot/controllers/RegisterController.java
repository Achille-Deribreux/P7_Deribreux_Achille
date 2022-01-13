package com.nnk.springboot.controllers;

import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.Service.UserService;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);

    @GetMapping
    public String showRegisterForm(Model model) {
        LOGGER.info("Get request at /register");
        return "/registerForm";
    }

    @PostMapping
    public String validate(@Valid User user, BindingResult result, Model model) {
        LOGGER.info("Post request at /register");
        if (!result.hasErrors()) {
            if(userService.uniqueUsernameValidator(user.getUsername())) {
                LOGGER.info("Unique username : ok");
                if(userService.validatePassword(user.getPassword())){
                    LOGGER.info("Valid password");
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    user.setPassword(encoder.encode(user.getPassword()));
                    LOGGER.info("Password has been encoded");
                    userService.add(user);
                    LOGGER.info("User "+user.getUsername()+" has been saved");
                    return "redirect:/login?success";
                }
                else{
                    LOGGER.info("Invalid password");
                    model.addAttribute("passwordError","Invalid password, it must have uppercase letters, digits and special character");
                }
            }
            else{
                LOGGER.info("Username already in DB");
                model.addAttribute("userError","Username already known, please log in");
            }
        }
        return "/registerForm";
    }
}
