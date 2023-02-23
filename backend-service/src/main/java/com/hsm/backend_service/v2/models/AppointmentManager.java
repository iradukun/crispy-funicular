package com.hcm.backend_service.v2.models;

import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ApiModel(value = "AppointmentManager", description = "The model for appointment manager")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="appointmentManager")
@PrimaryKeyJoinColumn(name = "appointmentManager_id")
public class AppointmentManager extends User {

    @NotNull(message = "Hospital Id is required")
    @ManyToOne
    private Hospital hospital;

    public AppointmentManager(String email, String username, String fullName, String profilePicPath, Role role, String password, String confirmPassword, Hospital hospital) {
        super(email, username, fullName, profilePicPath, role, password, confirmPassword);
        this.hospital = hospital;
    }
    public AppointmentManager(String email, String mobile, EGender gender, String fullName, EUserStatus status, com.hcm.backend_service.v2.fileHandling.File profileImage, String password, String confirmPassword) {
        super(email, mobile, gender, fullName, status, profileImage, password, confirmPassword);
    }

    public AppointmentManager(String email, String mobile, String fullName, Role role, String password, String confirmPassword, Hospital hospital) {

    }
}
