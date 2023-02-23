package com.prwebsitebackend.dto;

import lombok.Data;

@Data
public class ConnectionAccountDto {
    private String name;
    private String id;
    private String password;
    private String verifyPassword;
    private String authority;
}
