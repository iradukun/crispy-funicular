package com.prwebsitebackend.dto;

import com.prwebsitebackend.enums.CastingResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminListDto {
    private boolean turn;
    private String companyName;
    private Date appointmentDate;
    private Long numberOfPeople;
    private String address;
    private String keyman;
    private double contractAmount;
    private CastingResult vendor;
    private String businessName;

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
}
