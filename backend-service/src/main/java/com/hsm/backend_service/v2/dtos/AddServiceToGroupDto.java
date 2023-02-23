package com.hcm.backend_service.v2.dtos;

import com.hcm.backend_service.v2.models.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddServiceToGroupDto {
    private String service;
}
