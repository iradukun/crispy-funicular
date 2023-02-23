package com.hcm.backend_service.v2.dtos;

import com.hcm.backend_service.v2.security.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ResetPasswordDTO {

    @NotNull
    private String email;

    @NotNull
    private String activationCode;

    @ValidPassword
    private String password;
}
