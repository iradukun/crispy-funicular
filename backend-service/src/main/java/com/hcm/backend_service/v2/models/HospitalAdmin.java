package com.hcm.backend_service.v2.models;

import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@ApiModel(value = "HospitalAdmin", description = "The model for hospital admin")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="hospitalAdmin")
@PrimaryKeyJoinColumn(name = "hospitalAdmin_id")
public class HospitalAdmin extends User {

    @NotNull(message = "Hospital Id is required")
    @ManyToOne
    private Hospital hospital;
    public HospitalAdmin(String email, String mobile, EGender gender, String fullName, EUserStatus status, com.hcm.backend_service.v2.fileHandling.File profileImage, String password, String confirmPassword) {
        super(email, mobile, gender, fullName, status, profileImage, password, confirmPassword);
    }
}
