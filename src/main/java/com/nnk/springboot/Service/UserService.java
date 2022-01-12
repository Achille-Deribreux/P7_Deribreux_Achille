package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjetNotFoundExceptionString;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new ObjetNotFoundExceptionString("User", "username :"+username));
    }

    public Boolean validatePassword(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 16),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1)));

        RuleResult result = validator.validate(new PasswordData(password));

        return result.isValid();
    }

    public Boolean uniqueUsernameValidator(String username){
        User user = userRepository.findByUsername(username).orElse(null);
        //TODO TEST then try to simplify
        if(user == null){
            return true;
        }
        else{
            return false;
        }
    }

    public User add(User user){
        return userRepository.save(user);
    }
}
