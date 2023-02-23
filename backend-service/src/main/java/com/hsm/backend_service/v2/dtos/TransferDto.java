package com.hcm.backend_service.v2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {
    private UUID fromHospital;
    private UUID toHospital;
    private UUID service;
    private UUID patient;
    private String date;
}
