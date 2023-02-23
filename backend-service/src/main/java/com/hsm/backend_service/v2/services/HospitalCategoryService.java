package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.HospitalCategoryDto;
import com.hcm.backend_service.v2.enums.EHospitalCategory;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.models.HospitalCategory;
import com.hcm.backend_service.v2.repositories.HospitalCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HospitalCategoryService {
    @Autowired
    HospitalCategoryRepository hospitalCategoryRepository;
    public HospitalCategory getHospitalCategoryById(UUID hospitalCategoryId) {
        Optional<HospitalCategory> hospitalCategoryOptional =
                hospitalCategoryRepository.findById(hospitalCategoryId);
        if(!hospitalCategoryOptional.isPresent()) {
            throw new ElementNotFoundException("hospital category not found");
        }
        return hospitalCategoryOptional.get();
    }
    public List<HospitalCategory> getAllHospitalCategories() {
        return hospitalCategoryRepository.findAll();
    }
    public HospitalCategory createNewHospitalCategory(HospitalCategoryDto categoryDto) {
        HospitalCategory category =
                new HospitalCategory(EHospitalCategory.valueOf(categoryDto.getHospitalCategory()));
        return hospitalCategoryRepository.save(category);
    }
}
