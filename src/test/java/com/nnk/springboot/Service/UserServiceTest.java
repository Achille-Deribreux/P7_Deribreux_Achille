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
import static org.mockito.Mockito.verify;

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

    @Test
    void validatePasswordFalseTest(){
        //Given
        String password = "novalid";
        Boolean expected = false;
        Boolean result ;
        //When
        result = userService.validatePassword(password);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void validatePasswordTrueTest(){
        //Given
        String password = "Valid-12345";
        Boolean expected = true;
        Boolean result ;
        //When
        result = userService.validatePassword(password);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void uniqueUsernameValidatorTrueTest() {
        //Given
        String username ="achille";
        Boolean expected = true;
        Boolean result;
        //When
        result = userService.uniqueUsernameValidator(username);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void uniqueUsernameValidatorFalseTest() {
        //Given
        String username ="achille";
        User user = new User(1, "achille", "mdp", "Achille Deribreux","ADMIN");
        Boolean expected = false;
        Boolean result;
        //When
        Mockito.when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user));
        result = userService.uniqueUsernameValidator(username);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void addTest() {
        //Given
        User user = new User(1, "achille", "mdp", "Achille Deribreux","ADMIN");
        //When
        Mockito.when(userRepository.save(user)).thenReturn(user);
        userService.add(user);
        //Then
        verify(userRepository,Mockito.times(1)).save(user);
    }
}
