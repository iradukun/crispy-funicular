package com.hcm.backend_service.v2.payload;

import com.hcm.backend_service.v2.enums.EHospitalCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalCategoryPayload {
    private EHospitalCategory hospitalCategory;
}
