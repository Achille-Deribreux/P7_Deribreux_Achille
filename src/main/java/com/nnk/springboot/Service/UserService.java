package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjetNotFoundExceptionString;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //TODO : test class

    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new ObjetNotFoundExceptionString("User", "username :"+username));
    }
}
