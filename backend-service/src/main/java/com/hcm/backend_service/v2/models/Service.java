package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ApiModel(value = "Service", description = "The model for service")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service")
public class Service extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID service_id;

    @NotNull(message = "Service is required.")
    @Column(unique = true)
    private String service;

    @JsonIgnore
    @ManyToMany(mappedBy = "services", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Hospital> hospitals = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "departments", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Doctor> doctors = new ArrayList<>();

    @JsonIgnore
//    OneToMany with schedule
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "service", orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Group> groups = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Service service = (Service) o;
        return service_id != null && Objects.equals(service_id, service.service_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Service(String service) {
        this.service = service;
    }
}
