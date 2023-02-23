package com.hcm.backend_service.v2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "Email cannot be blank")
    private String username;
    @NotNull(message = "Password cannot be blank")
    private String password;
}
