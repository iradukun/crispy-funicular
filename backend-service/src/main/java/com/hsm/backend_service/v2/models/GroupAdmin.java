package com.hcm.backend_service.v2.models;

import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.fileHandling.File;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "GroupAdmin", description = "The model for group admin")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="groupAdmin")
@PrimaryKeyJoinColumn(name = "groupAdmin_id")
public class GroupAdmin extends User{
    @NotNull(message = "Group Id is required.")
    @ManyToOne
    private Group group;

    public GroupAdmin(String email, String mobile, EGender gender, String fullName, EUserStatus status, File profileImage, String password, String confirmPassword) {
        super(email, mobile, gender, fullName, status, profileImage, password, confirmPassword);
    }

    public GroupAdmin(String email, String username, String fullName, String profilePicPath, Role role, String password, String confirmPassword) {
        super(email, username, fullName, profilePicPath, role, password, confirmPassword);
    }

    public GroupAdmin(String email, String fullName, Role role, String password, String confirmPassword, Group group) {
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
