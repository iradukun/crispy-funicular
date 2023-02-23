package com.hcm.backend_service.v2.dtos;

import com.hcm.backend_service.v2.enums.EMessageVia;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class BookAppointmentDto {
    @Enumerated(EnumType.STRING)
    private EMessageVia via;

    @NotBlank(message = "please supply phone or email")
    private String phoneOrEmailVia;
}
