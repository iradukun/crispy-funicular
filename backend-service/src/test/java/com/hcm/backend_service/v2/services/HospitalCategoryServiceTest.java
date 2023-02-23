package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.models.HospitalCategory;
import com.hcm.backend_service.v2.repositories.HospitalCategoryRepository;
import com.hcm.backend_service.v2.enums.EHospitalCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HospitalCategoryServiceTest {
    @Autowired
    HospitalCategoryRepository hospitalCategoryRepository;
    @Test
    public HospitalCategory createHospitalCategory() {
        HospitalCategory hospitalCategory =
                new HospitalCategory(EHospitalCategory.REFERRAL);
        System.out.println(hospitalCategory);
        return hospitalCategoryRepository.save(hospitalCategory);
    }
}