package com.hcm.backend_service.v2.models;

import com.hcm.backend_service.v2.audits.InitiatorAudit;
import com.hcm.backend_service.v2.enums.EScheduleStatus;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "ScheduleStatus", description = "The model for schedule status")
@Entity
@Table(name = "schedule_status")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleStatus extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID schedule_status_id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Schedule status is required.")
    @Column(unique = true)
    private EScheduleStatus scheduleStatus;

//    OneToMany with schedule
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "status", orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();
}
