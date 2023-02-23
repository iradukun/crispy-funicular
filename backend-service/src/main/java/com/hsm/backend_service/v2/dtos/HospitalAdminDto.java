package com.hcm.backend_service.v2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalAdminDto extends SignUpDTO {
    private UUID hospitalId;
}
