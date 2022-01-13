package com.nnk.springboot.Service;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class AuthenticationService implements UserDetailsService {

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationService.class);

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method who load a UserDetails user from a username
     * @Param  username (String)
     * @Return  user (UserDetails)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("loadUser :"+username);
        User user = userService.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
