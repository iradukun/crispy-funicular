package com.prwebsitebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CastingReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String employeeTeam;
    private String caster;
    private int countOfCasting;
    private int countOfPR;
    private float percentage;
    private String briefingPersonnel;
    private int averageOfPerson;

    public CastingReport(String employeeTeam, String caster, int countOfCasting, int countOfPR, float percentage, String briefingPersonnel, int averageOfPerson) {
        this.employeeTeam = employeeTeam;
        this.caster = caster;
        this.countOfCasting = countOfCasting;
        this.countOfPR = countOfPR;
        this.percentage = percentage;
        this.briefingPersonnel = briefingPersonnel;
        this.averageOfPerson = averageOfPerson;
    }

    public String getEmployeeTeam() {
        return employeeTeam;
    }

    public void setEmployeeTeam(String employeeTeam) {
        this.employeeTeam = employeeTeam;
    }

    public String getCaster() {
        return caster;
    }

    public void setCaster(String caster) {
        this.caster = caster;
    }

    public int getCountOfCasting() {
        return countOfCasting;
    }

    public void setCountOfCasting(int countOfCasting) {
        this.countOfCasting = countOfCasting;
    }

    public int getCountOfPR() {
        return countOfPR;
    }

    public void setCountOfPR(int countOfPR) {
        this.countOfPR = countOfPR;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getBriefingPersonnel() {
        return briefingPersonnel;
    }

    public void setBriefingPersonnel(String briefingPersonnel) {
        this.briefingPersonnel = briefingPersonnel;
    }

    public int getAverageOfPerson() {
        return averageOfPerson;
    }

    public void setAverageOfPerson(int averageOfPerson) {
        this.averageOfPerson = averageOfPerson;
    }
//    @ManyToOne(
//            cascade = CascadeType.ALL
//    )
//    private Caster casterAssigned;
//    @ManyToOne(
//            cascade = CascadeType.ALL
//    )
//    private Salesman salesmanAssigned;
}
