package com.hcm.backend_service.v2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddServiceToHospitalDto {
    @NotNull(message = "service is required")
    private UUID serviceId;
    @NotNull(message = "group is required")
    private UUID groupId;
    @NotNull(message = "booking fee is required")
    private Long fee;
    @NotNull(message = "currency is required")
    private String currency;
}
