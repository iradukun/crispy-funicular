package com.hcm.backend_service.v2.dtos;

import com.hcm.backend_service.v2.enums.EMessageVia;
import com.hcm.backend_service.v2.models.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendAppointmentDto {
    @NotNull(
            message = "schedule id is required"
    )
    private UUID scheduleId;
    @NotNull(
            message = "email or mobile should be specified"
    )
    private EMessageVia via;
    private String emailOrPhoneVia;
    private String service;
    private String start_time;
    private String end_time;
}
