package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    private Set<Integer> hour;
    @ElementCollection
    private Set<String> staff;
    @Column()
    @CreationTimestamp
    private LocalDate day;
    @Column()
    private String address;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "schedule")
    private SuccessRates successRates;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "schedule")
    private CastCount castCount;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;



    public SuccessRates getSuccessRates() {
        return successRates;
    }

    public void setSuccessRates(SuccessRates successRates) {
        this.successRates = successRates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CastCount getCastCount() {
        return castCount;
    }

    public void setCastCount(CastCount castCount) {
        this.castCount = castCount;
    }

    public Set<Integer> getHour() {
        return hour;
    }

    public void setHour(Set<Integer> hour) {
        this.hour = hour;
    }

    public Set<String> getStaff() {
        return staff;
    }

    public void setStaff(Set<String> staff) {
        this.staff = staff;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
}
