package com.hcm.backend_service.v2.models;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.Cache;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@ApiModel(value = "Appointment", description = "The model for appointment")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID transfer_id;

    @ManyToOne(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER
    )
    private Hospital from;
    @ManyToOne(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER
    )
    private Hospital to;
    @ManyToOne(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER
    )
    private Service service;
    @ManyToOne(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER
    )
    private User patient;
    private Date date;

    public Transfer(Hospital from, Hospital to, Service service, User patient, Date date) {
        this.from = from;
        this.to = to;
        this.service = service;
        this.patient = patient;
        this.date = date;
    }
}
