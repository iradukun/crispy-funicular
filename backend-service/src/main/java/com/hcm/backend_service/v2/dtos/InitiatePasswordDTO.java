package com.hcm.backend_service.v2.dtos;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class InitiatePasswordDTO {

    @NotNull
    @Email
    private String email;
}
