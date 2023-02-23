package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
@ApiModel(value = "Group", description = "The model for group of hospitals")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"group\"")
public class Group extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID group_id;

    @NotNull(message = "Group name is required.")
    private String groupName;

    private String groupEmail;

    @JsonIgnore
    @ManyToOne(
            cascade = CascadeType.REFRESH, fetch = FetchType.LAZY
    )
    private SuperAdmin superAdmin;
    @JsonIgnore
//    OneToMany with hospital
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "group", orphanRemoval = false)
    private List<Hospital> hospitals = new ArrayList<>();

    @JsonIgnore
//    OneToMany with hospital
    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "group", orphanRemoval = true)
    private List<GroupAdmin> groupAdmins = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Service> services = new ArrayList<>();

    public SuperAdmin getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(SuperAdmin superAdmin) {
        this.superAdmin = superAdmin;
    }

    public Group(String groupName, String groupEmail) {
        this.groupName = groupName;
        this.groupEmail = groupEmail;
    }

    public UUID getGroup_id() {
        return group_id;
    }

    public void setGroup_id(UUID group_id) {
        this.group_id = group_id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupEmail() {
        return groupEmail;
    }

    public void setGroupEmail(String groupEmail) {
        this.groupEmail = groupEmail;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public List<GroupAdmin> getGroupAdmins() {
        return groupAdmins;
    }

    public void setGroupAdmins(List<GroupAdmin> groupAdmins) {
        this.groupAdmins = groupAdmins;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
