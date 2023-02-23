package com.prwebsitebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String username;
    private String id;
    private String password;
    private String verifyPassword;
    private String authority;
}
