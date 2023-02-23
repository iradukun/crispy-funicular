package com.hcm.backend_service.v2.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ChangeScheduleDto {
    private UUID appointmentId;
    private UUID oldScheduleId;
    private UUID newScheduleId;
}
