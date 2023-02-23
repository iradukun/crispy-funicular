package com.hcm.backend_service.v2.dtos;

import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.ERole;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class UpdateUserDTO {
    @Email
    private String email;

    @Pattern(regexp = "[0-9]{12}")
    private String mobile;

    private EGender gender;

    @NotNull
    private String fullName;

    private ERole role;
}