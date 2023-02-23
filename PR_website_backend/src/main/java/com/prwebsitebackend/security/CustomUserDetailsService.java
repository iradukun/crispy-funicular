package com.prwebsitebackend.security;


import com.prwebsitebackend.model.User;
import com.prwebsitebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    // Injecting Dependencies
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch User from the DB
        User user = userRepository.findByUsername(username);
        // No user found
        if(user == null)
            throw new UsernameNotFoundException("Could not findUser with username = " + username + " and that password");
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))); // Sets the role of the found user
    }
}

