package com.hcm.backend_service.v2.dtos;


import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.security.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @Email
    private  String email;

    @NotNull
    private  String mobile;

    private String gender;

    @NotNull
    private  String fullName;

    private String role;

    @ValidPassword
    private  String password;

    @ValidPassword
    private  String confirmPassword;

}
