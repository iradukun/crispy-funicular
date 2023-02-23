package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.fileHandling.File;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "SuperAdmin", description = "The model for super admin")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="superAdmin")
@PrimaryKeyJoinColumn(name = "superAdmin_id")
public class SuperAdmin extends User {

//    OneToMany with group

    @JsonIgnore
    @OneToMany(
            mappedBy = "superAdmin",
            fetch = FetchType.LAZY
    )
    private List<Group> groups = new ArrayList<>();

    public SuperAdmin(String email, String mobile, EGender gender, String fullName, EUserStatus status, com.hcm.backend_service.v2.fileHandling.File profileImage, String password, String confirmPassword) {
        super(email, mobile, gender, fullName, status, profileImage, password, confirmPassword);
    }

//    public SuperAdmin(String email, String fullName, Role role, String password, String confirmPassword) {
//    }
}
