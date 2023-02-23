package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "Schedule", description = "The model for schedule")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule")
public class Schedule extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID schedule_id;

    @NotNull(message = "Scheduler is required.")
    @ManyToOne
    @JsonIgnore
    private User scheduler;

    @NotNull(message = "Service is required.")
    @ManyToOne
    @JsonIgnore
    private Service service;

    @NotNull(message = "Doctor is required.")
    @ManyToOne
    @JsonIgnore
    private User doctor;

    @NotNull(message = "Hospital Id is required")
    @ManyToOne
    private Hospital hospital;

    @NotNull(message = "Schedule status is required.")
    @ManyToOne
    private ScheduleStatus status;

    @NotNull(message = "Start date is required")
    private Date start_date;

    @NotNull(message = "End date is required")
    private Date end_date;

    @NotNull(message = "Start time is required")
    private Time start_time;

    @NotNull(message = "End time is required")
    private Time end_time;


//    OneToMany with appointment
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "schedule", orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public Schedule(Date start_date, Date end_date, Time start_time, Time end_time) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Schedule(ScheduleStatus status, Date start_date, Date end_date, Time start_time, Time end_time) {
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
