package com.hcm.backend_service.v2.models;

import com.hcm.backend_service.v2.audits.InitiatorAudit;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "DoctorHospitalServices", description = "The model for services of a doctor in given hospital")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor_hospital_services")
public class DoctorHospitalServices extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID id;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    private Hospital hospital;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    private User doctor;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    private List<Service> doctorServices;

    public DoctorHospitalServices(Hospital hospital, User doctor, List<Service> doctorServices) {
        this.hospital = hospital;
        this.doctor = doctor;
        this.doctorServices = doctorServices;
    }
}
