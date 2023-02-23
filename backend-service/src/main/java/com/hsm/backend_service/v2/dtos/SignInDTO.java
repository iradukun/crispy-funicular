package com.hcm.backend_service.v2.dtos;


import com.hcm.backend_service.v2.security.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class SignInDTO {

    @NotNull
    @Email
    private  String email;

    @ValidPassword
    private  String password;
}

