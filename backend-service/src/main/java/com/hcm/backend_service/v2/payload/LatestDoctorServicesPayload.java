package com.hcm.backend_service.v2.payload;

import com.hcm.backend_service.v2.models.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LatestDoctorServicesPayload {
    private Service service;
    private Long appointmentsNumber;

}
