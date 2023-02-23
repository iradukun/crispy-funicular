package com.hcm.backend_service.v2.security;

import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Qualifier("IUserRepository")
    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public UserDetails loadByUserId(UUID id) {
        System.out.println(id);
        User user = this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: "+id));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+String.valueOf(user.getRole().getRole())))
        );
    }


    @Transactional
    public UserDetails loadUserByUsername(String s) {
        User user = this.userRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("User not found with email: "+s));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+String.valueOf(user.getRole().getRole())))
        );
    }
}
