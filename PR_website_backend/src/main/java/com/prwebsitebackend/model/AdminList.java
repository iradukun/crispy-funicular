package com.prwebsitebackend.model;

import com.prwebsitebackend.enums.CastingResult;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AdminList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean turn;
    private String companyName;
    private Date appointmentDate;
    private Long numberOfPeople;
    private String address;
    private String keyman;
    private double contractAmount;
    private CastingResult vendor;
    private String businessName;

    public AdminList(boolean turn, String companyName, Long numberOfPeople, String address,
                        String keyman, double contractAmount, CastingResult vendor, String businessName) {
        this.turn = turn;
        this.companyName = companyName;
        this.numberOfPeople = numberOfPeople;
        this.address = address;
        this.keyman = keyman;
        this.contractAmount = contractAmount;
        this.vendor = vendor;
        this.businessName = businessName;
    }

    public boolean isTurn() {
        return turn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Date getDateTime() {
        return appointmentDate;
    }

    public Long getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getPublicRelations() {
        return address;
    }

    public String getSalesperson() {
        return keyman;
    }

    public double getContractAmount() {
        return contractAmount;
    }

    public CastingResult getResult() {
        return vendor;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDateTime(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setNumberOfPeople(Long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setPublicRelations(String address) {
        this.address = address;
    }

    public void setSalesperson(String keyman) {
        this.keyman = keyman;
    }

    public void setContractAmount(double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public void setResult(CastingResult vendor) {
        this.vendor = vendor;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
