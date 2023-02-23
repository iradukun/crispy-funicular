package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.fileHandling.File;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="doctor")
@PrimaryKeyJoinColumn(name = "doctor_id")
public class Doctor extends User {
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "doctor_service", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> departments = new ArrayList<>();

    @NotNull(message = "Hospital is required.")
    @ManyToOne
    @JsonIgnore
    private Hospital hospital;

    @JsonIgnore
//    OneToMany with schedule
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "doctor", orphanRemoval = false)
    private List<Schedule> schedules = new ArrayList<>();

    public Doctor(String email, String fullName, String password, String confirmPassword, EGender gender, String mobile, Role role, List<Service> doctorServices, Hospital hospital) {
        super(email, fullName, password, confirmPassword, gender, mobile, role);
        this.setEmail(email);
        this.setFullName(fullName);
        this.setPassword(password);
        this.setConfirmPassword(confirmPassword);
        this.setGender(gender);
        this.setMobile(mobile);
        this.setRole(role);
        this.departments = doctorServices;
        this.hospital = hospital;
    }
}
