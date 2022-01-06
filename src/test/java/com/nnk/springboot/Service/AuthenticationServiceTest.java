package com.nnk.springboot.Service;

import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class AuthenticationServiceTest {


    @MockBean
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService.setUserService(userService);
    }

    @Test
    void loadUserByUsernameTest() {
        //Given
        String username = "Achille";
        User user = new User(1,"Achille","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        UserDetails expected = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

        UserDetails result;
        //When
        Mockito.when(userService.findByUsername(username)).thenReturn(user);
        result = authenticationService.loadUserByUsername(username);
        //Then
        Assertions.assertEquals(expected, result);
    }
}
