package com.prwebsitebackend.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class SuccessRatesDTO {
    private int successRate1;
    private int successRate2;
    private int successRate3;

    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    private Date startDate;
    private Date endDate;

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
}
