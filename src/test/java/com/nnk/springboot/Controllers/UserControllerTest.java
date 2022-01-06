package com.nnk.springboot.Controllers;

import com.nnk.springboot.Service.AuthenticationService;
import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    AuthenticationService authenticationService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    User user = new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");

    @Test
    void homeTest() throws Exception {
        //Given
        List<User> findAll = new ArrayList<>(Arrays.asList(new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN"),new User(2,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN")));
        //When
        Mockito.when(userRepository.findAll()).thenReturn(findAll);
        //Then
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", findAll))
                .andExpect(view().name("user/list"));
    }

    @Test
    void addUserTest() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    void validateTest() throws Exception {
        //Given
        User user =  new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        List<User> findAll = new ArrayList<>(Arrays.asList(new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN"),new User(2,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN")));
        //When
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findAll()).thenReturn(findAll);
        //Then
        mockMvc.perform(post("/user/validate").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username","username")
                        .param("password","user")
                        .param("fullname","full name")
                        .param("role","ADMIN")
                .with(csrf())).andExpect(redirectedUrl("/user/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        User user =  new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));

        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.model().attribute("user", user))
                .andExpect(view().name("user/update"));
    }


    @Test
    void updateUserTest() throws Exception {
        //Given
        User user =  new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        //When
        Mockito.when(userRepository.save(user)).thenReturn(user);
        //Then
        mockMvc.perform(post("/user/update/1").contentType(MediaType.APPLICATION_FORM_URLENCODED) .param("username","username")
                .param("password","user")
                .param("fullname","full name")
                .param("role","ADMIN")
                .with(csrf())).andExpect(redirectedUrl("/user/list"));
    }

    @Test
    void deleteUserTest() throws Exception {
        //Given
        User user =  new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        List<User> findAll = new ArrayList<>(Arrays.asList(new User(1,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN"),new User(2,"username","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN")));
        //When
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        Mockito.when(userRepository.findAll()).thenReturn(findAll);
        Mockito.doNothing().when(userRepository).delete(user);
        //Then
        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/list"));
    }
}
