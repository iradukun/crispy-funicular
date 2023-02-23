package com.prwebsitebackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"schedule_id"})})
public class CastCount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long castCount;
    private Date startDate;
    private Date endDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public CastCount(Long castCount, Date startDate, Date endDate) {
        this.castCount = castCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CastCount() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCastCount() {
        return castCount;
    }

    public void setCastCount(Long castCount) {
        this.castCount = castCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
