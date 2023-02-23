package com.prwebsitebackend.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "success")
@NoArgsConstructor
@AllArgsConstructor
public class SuccessRates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long success_id;
    private int successRate1;
    private int successRate2;
    private int successRate3;
    private int result;
    private Date startDate;
    private Date endDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public SuccessRates(int successRate1, int successRate2, int successRate3, Date startDate, Date endDate) {
        this.successRate1 = successRate1;
        this.successRate2 = successRate2;
        this.successRate3 = successRate3;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public int getSuccessRate1() {
        return successRate1;
    }

    public void setSuccessRate1(int successRate1) {
        this.successRate1 = successRate1;
    }

    public int getSuccessRate2() {
        return successRate2;
    }

    public void setSuccessRate2(int successRate2) {
        this.successRate2 = successRate2;
    }

    public int getSuccessRate3() {
        return successRate3;
    }

    public void setSuccessRate3(int successRate3) {
        this.successRate3 = successRate3;
    }

    public Long getSuccess_id() {
        return success_id;
    }

    public void setSuccess_id(Long success_id) {
        this.success_id = success_id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
