package com.example.springsecurityjwttutorial.controllers;

import com.example.springsecurityjwttutorial.entity.User;
import com.example.springsecurityjwttutorial.exceptions.ResourceNotFound;
import com.example.springsecurityjwttutorial.models.AuthResponse;
import com.example.springsecurityjwttutorial.models.LoginCredentials;
import com.example.springsecurityjwttutorial.repository.UserRepo;
import com.example.springsecurityjwttutorial.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController // Marks the class a rest controller
@RequestMapping("/api/auth") // Requests made to /api/auth/anything will be handles by this class
public class AuthController {

    // Injecting Dependencies
    @Autowired private UserRepo userRepo;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    // Defining the function to handle the POST route for registering a user
    @PostMapping("/register")
    public AuthResponse registerHandler(@RequestBody User user){
        // Encoding Password using Bcrypt
        String encodedPass = passwordEncoder.encode(user.getPassword());

        // Setting the encoded password
        user.setPassword(encodedPass);

        user.setRole("ROLE_BOOKER");

        // Persisting the User Entity to H2 Database
        user = userRepo.save(user);

        // Generating JWT
        String token = jwtUtil.generateToken(user.getPhone());

        // Responding with JWT with user

        AuthResponse authResponse = new AuthResponse(token, user);

        return authResponse;
    }

    // Defining the function to handle the POST route for logging in a user
    @PostMapping("/login")
    public AuthResponse loginHandler(@RequestBody LoginCredentials body){
        try {
            // Creating the Authentication Token which will contain the credentials for authenticating
            // This token is used as input to the authentication process
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getPhone(), body.getPassword());

            // Authenticating the Login Credentials
            authManager.authenticate(authInputToken);

            // If this point is reached it means Authentication was successful
            // Generate the JWT
            String token = jwtUtil.generateToken(body.getPhone());

            // Respond with the JWT with user

            Optional<User> user = userRepo.findByPhone(body.getPhone());
            User authUser = user.get();

            AuthResponse authResponse = new AuthResponse(token, authUser);
            return authResponse;
        }catch (AuthenticationException authExc){
            // Auhentication Failed
            throw new ResourceNotFound("Invalid Login Credentials");
        }
    }


}
