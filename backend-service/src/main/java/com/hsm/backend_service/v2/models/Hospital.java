package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import io.swagger.annotations.ApiModel;
import lombok.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "Hospital", description = "The model for hospital")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospital")
public class Hospital extends InitiatorAudit {
    public UUID getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(UUID hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HospitalCategory getHospitalCategory() {
        return hospitalCategory;
    }

    public List<Transfer> getReceivedTransfers() {
        return receivedTransfers;
    }

    public void setReceivedTransfers(List<Transfer> receivedTransfers) {
        this.receivedTransfers = receivedTransfers;
    }

    public List<Transfer> getSentTransfers() {
        return sentTransfers;
    }

    public void setSentTransfers(List<Transfer> sentTransfers) {
        this.sentTransfers = sentTransfers;
    }

    public void setHospitalCategory(HospitalCategory hospitalCategory) {
        this.hospitalCategory = hospitalCategory;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<HospitalAdmin> getHospitalAdmins() {
        return hospitalAdmins;
    }

    public void setHospitalAdmins(List<HospitalAdmin> hospitalAdmins) {
        this.hospitalAdmins = hospitalAdmins;
    }

    public List<AppointmentManager> getAppointmentManagers() {
        return appointmentManagers;
    }

    public void setAppointmentManagers(List<AppointmentManager> appointmentManagers) {
        this.appointmentManagers = appointmentManagers;
    }

    public List<ScheduleManager> getScheduleManagers() {
        return scheduleManagers;
    }

    public void setScheduleManagers(List<ScheduleManager> scheduleManagers) {
        this.scheduleManagers = scheduleManagers;
    }

    public List<BookingFee> getBookingFees() {
        return bookingFees;
    }

    public void setBookingFees(List<BookingFee> bookingFees) {
        this.bookingFees = bookingFees;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID hospitalId;

    @NotNull(message = "Hospital name is required.")
    @Column(unique = true)
    private String hospitalName;

    @NotNull(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Group is required.")
    @ManyToOne
    @JsonIgnore
    private Group group;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "hospital_service", joinColumns = @JoinColumn(name = "hospital_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    @ToString.Exclude
    private List<Service> services = new ArrayList<>();

    @NotNull(message = "Location is required.")
    private String location;

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @ToString.Exclude
    private HospitalCategory hospitalCategory;

//    OneToMany with doctor
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "hospital", orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Doctor> doctors = new ArrayList<>();

//    OneToMany with schedule
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "hospital", orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

//    OneToMany with hospital admin
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "hospital", orphanRemoval = true)
    private List<HospitalAdmin> hospitalAdmins = new ArrayList<>();

//    OneToMany with appointment manager
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "hospital", orphanRemoval = true)
    private List<AppointmentManager> appointmentManagers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "hospital", orphanRemoval = true)
    private List<ScheduleManager> scheduleManagers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "hospital"
    )
    private List<BookingFee> bookingFees;

    @OneToMany(
            mappedBy = "to",
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Transfer> receivedTransfers;

    @OneToMany(
            mappedBy = "from",
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Transfer> sentTransfers;
    public Hospital(String hospitalName, String email, String location) {
        this.hospitalName = hospitalName;
        this.email = email;
        this.location = location;
    }
}
