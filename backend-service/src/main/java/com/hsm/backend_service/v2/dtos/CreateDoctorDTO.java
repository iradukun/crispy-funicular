package com.hcm.backend_service.v2.dtos;

import com.hcm.backend_service.v2.security.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDoctorDTO {

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

    @NotNull
    private UUID hospitalId;

    @NotNull(message = "doctor needs at least one service")
    private List<UUID> services;
}
