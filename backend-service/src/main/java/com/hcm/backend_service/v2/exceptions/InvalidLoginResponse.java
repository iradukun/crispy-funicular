package com.hcm.backend_service.v2.exceptions;

public class InvalidLoginResponse {
    private String email;
    private String password;

    private String error;

    public InvalidLoginResponse() {
        this.error = "Invalid email or password";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
