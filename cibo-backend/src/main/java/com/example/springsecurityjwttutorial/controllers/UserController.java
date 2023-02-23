package com.example.springsecurityjwttutorial.controllers;

import com.example.springsecurityjwttutorial.entity.Restourant;
import com.example.springsecurityjwttutorial.entity.User;
import com.example.springsecurityjwttutorial.repository.RestourantRepo;
import com.example.springsecurityjwttutorial.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;


@RestController // Marks the class a rest controller
@RequestMapping("/api/user") // Requests made to /api/auth/anything will be handles by this class
public class UserController {

    // Injecting Dependencies
    @Autowired private UserRepo userRepo;
    @Autowired private RestourantRepo restourantRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    // Defining the function to handle the GET route to fetch user information of the authenticated user
//    @GetMapping("/info")
//    public User getUserDetails(){
//        // Retrieve email from the Security Context
//        String phone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // Fetch and return user details
//        return userRepo.findByPhone(phone).get();
//    }

    //create a user

    @PostMapping("{restourantId}")
    @Transactional
    public User createUser(@RequestBody User user, @PathVariable Long restourantId) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_WORKER");
        User newUser = userRepo.save(user);
        Restourant restourant = restourantRepo.findById(restourantId).orElse(null);
        restourant.getEmployees().add(newUser);
        restourantRepo.save(restourant);
        newUser.setRestourant(restourant);
        userRepo.save(newUser);
        return newUser;
    }
}
