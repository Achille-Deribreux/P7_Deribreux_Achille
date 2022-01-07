package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.Exceptions.CustomExceptions.ObjetNotFoundExceptionString;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
        userService.setUserRepository(userRepository);
    }

    @Test
    void findByUsernameTest() {
       //Given
        String username = "Achille";
        User user = new User(1,"Achille","$2a$10$HsDretUSp5zcazogb8UEte383OX5K.6Anz1rte1x0426ZnYLR/MUW","full name","ADMIN");
        //When
        Mockito.when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user));
        //Then
        Assertions.assertEquals(user,userService.findByUsername(username));
    }

    @Test
    void findByUsernameExceptionTest() {
        assertThrows(ObjetNotFoundExceptionString.class,()->userService.findByUsername("Achille"));
    }
}
