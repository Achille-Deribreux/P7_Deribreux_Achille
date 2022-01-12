package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjetNotFoundExceptionString;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Method who call the findByUserName method of the repository to find an user by his username
     * @Param  username (String)
     * @Return  User corresponding to the username, or an ObjectNotFoundException(user)
     */
    public User findByUsername(String username) {
        LOGGER.info("Search user for username :"+username);
        return userRepository.findByUsername(username).orElseThrow(()-> new ObjetNotFoundExceptionString("User", "username :"+username));
    }

    /**
     * Method who check if the password is valid, check if it's between 8 and 16 Character and has min 1 UpperCase letter, 1 special character and 1 digit
     * @Param  password (String)
     * @Return  boolean if password is valid or not (Boolean)
     */
    public Boolean validatePassword(String password) {
        LOGGER.info("check if password is valid");
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 16),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1)));

        RuleResult result = validator.validate(new PasswordData(password));

        LOGGER.debug("password is valid ? "+result.isValid());
        return result.isValid();
    }

    /**
     * Method who calls the findByUsername repository method, to check if the user is already known in the database
     * @Param  username (String)
     * @Return  boolean if user is already in the database or not (Boolean)
     */
    public Boolean uniqueUsernameValidator(String username){
        LOGGER.info("Check if user already exists for username :"+username);
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            LOGGER.info("No user for username :"+username);
            return true;
        }
        else{
            LOGGER.info("User already exists for username :"+username);
            return false;
        }
    }

    /**
     * Method who calls the save repository method, to save the User in the database
     * @Param  User (User)
     * @Return  User with the ID (User)
     */
    public User add(User user){
        LOGGER.info("Add user for username :" + user.getUsername());
        return userRepository.save(user);
    }
}
