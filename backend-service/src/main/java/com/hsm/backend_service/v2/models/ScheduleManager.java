package com.hcm.backend_service.v2.models;

import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "ScheduleManager", description = "The model for schedule manager")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="scheduleManager")
@PrimaryKeyJoinColumn(name = "scheduler_id")
public class ScheduleManager extends User {

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    private Hospital hospital;
//    OneToMany with schedule
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "scheduler", orphanRemoval = false)
    private List<Schedule> schedules = new ArrayList<>();


    public ScheduleManager(UUID user_id) {
        super(user_id);
    }
    public ScheduleManager(String email, String mobile, EGender gender, String fullName, EUserStatus status, com.hcm.backend_service.v2.fileHandling.File profileImage, String password, String confirmPassword) {
        super(email, mobile, gender, fullName, status, profileImage, password, confirmPassword);
    }
}
