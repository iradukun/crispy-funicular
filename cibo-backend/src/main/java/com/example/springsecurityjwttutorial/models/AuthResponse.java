package com.example.springsecurityjwttutorial.models;

import com.example.springsecurityjwttutorial.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private User user;
}
