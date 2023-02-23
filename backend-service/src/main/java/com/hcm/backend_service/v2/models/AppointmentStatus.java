package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import com.hcm.backend_service.v2.enums.EAppointmentStatus;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "AppointmentStatus", description = "The model for appointment status")
@Entity
@Table(name = "appointment_status")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentStatus extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID appointment_status_id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Appointment status is required.")
    @Column(unique = true)
    private EAppointmentStatus appointmentStatus;

//    OneToMany with appointment
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "status", orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentStatus(EAppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}
