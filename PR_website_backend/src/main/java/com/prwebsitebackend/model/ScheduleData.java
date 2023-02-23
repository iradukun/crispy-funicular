package com.prwebsitebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long scheduleId;
    private LocalDateTime scheduleDate;
    private String location;
    private String staff;
    private Integer hour;
    @CreationTimestamp
    private LocalDateTime createdAt;
    public ScheduleData(Long scheduleId, LocalDateTime createdAt, String location, String staff, Integer hour) {
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.location = location;
        this.staff = staff;
        this.hour = hour;
    }
}
