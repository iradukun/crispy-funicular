package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import com.hcm.backend_service.v2.enums.EMessageVia;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@ApiModel(value = "Appointment", description = "The model for appointment")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
public class Appointment extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID appointment_id;


    @JsonBackReference
    @ManyToOne
    private User patient;

    @NotNull(message = "Schedule is required.")
    @ManyToOne
    @JsonIgnore
    private Schedule schedule;

    @NotNull(message = "Appointment status is required.")
    @ManyToOne
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    private EMessageVia via;

    private String phoneOrEmailVia;

    @NotNull(message = "Start time is required")
    private Timestamp start_time;

    @NotNull(message = "End time is required")
    private Timestamp end_time;

    public Appointment(Schedule schedule, AppointmentStatus status, Timestamp start_time, Timestamp end_time) {
        this.schedule = schedule;
        this.status = status;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Appointment(Timestamp start_time, Timestamp end_time, EMessageVia via, String phoneOrEmailVia) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.via = via;
        this.phoneOrEmailVia = phoneOrEmailVia;
    }
}
