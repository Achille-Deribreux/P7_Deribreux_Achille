package com.nnk.springboot.Controllers;

import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.Service.UserService;
import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.controllers.RegisterController;
import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//@WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
@WebMvcTest(RegisterController.class)
public class RegistrerControllerTest {

    @MockBean
    AuthenticationService authenticationService;

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void showRegisterFormTest() throws Exception {
        mockMvc.perform(get("/register")).andExpect(view().name("/registerForm"));
    }

    @Test
    void validateTest() throws Exception {
        //Given
        User user =  new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        //When
        Mockito.when(userService.uniqueUsernameValidator(user.getUsername())).thenReturn(true);
        Mockito.when(userService.validatePassword("user")).thenReturn(true);
        Mockito.when(userService.add(user)).thenReturn(user);
        //Then
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","username")
                .param("password","user")
                .param("fullname","full name")
                .param("role","ADMIN")
                .with(csrf())).andExpect(redirectedUrl("/login?success"));
    }

    @Test
    void validateAlreadyKnownTest() throws Exception {
        //Given
        User user =  new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        //When
        Mockito.when(userService.uniqueUsernameValidator(user.getUsername())).thenReturn(false);
        Mockito.when(userService.validatePassword("user")).thenReturn(true);
        Mockito.when(userService.add(user)).thenReturn(user);
        //Then
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","username")
                .param("password","user")
                .param("fullname","full name")
                .param("role","ADMIN")
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userError"))
                .andExpect(MockMvcResultMatchers.model().attribute("userError", "Username already known, please log in"))
                .andExpect(view().name("/registerForm"));
    }

    @Test
    void validateInvalidPasswordTest() throws Exception {
        //Given
        User user =  new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        //When
        Mockito.when(userService.uniqueUsernameValidator(user.getUsername())).thenReturn(true);
        Mockito.when(userService.add(user)).thenReturn(user);
        //Then
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username","username")
                        .param("password","user")
                        .param("fullname","full name")
                        .param("role","ADMIN")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.model().attributeExists("passwordError"))
                .andExpect(MockMvcResultMatchers.model().attribute("passwordError", "Invalid password, it must have uppercase letters, digits and special character"))
                .andExpect(view().name("/registerForm"));
    }


}
