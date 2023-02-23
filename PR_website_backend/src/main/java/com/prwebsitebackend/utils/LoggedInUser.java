package com.prwebsitebackend.utils;

import com.prwebsitebackend.model.User;
import com.prwebsitebackend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoggedInUser {
    private final UserRepository userRepository;

    public LoggedInUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof String) {
            String username = (String) principal;
            User user =  userRepository.findByUsername(username);
            return user;
        }else{
            throw new RuntimeException("No User");
        }
    }
}

