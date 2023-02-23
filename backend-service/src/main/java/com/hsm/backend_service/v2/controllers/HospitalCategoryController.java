package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.HospitalCategoryDto;
import com.hcm.backend_service.v2.models.HospitalCategory;
import com.hcm.backend_service.v2.services.HospitalCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/hospital_category")
public class HospitalCategoryController {
    @Autowired
    HospitalCategoryService hospitalCategoryService;
    @GetMapping
    public List<HospitalCategory> getAllHospitalCategories() {
        return hospitalCategoryService.getAllHospitalCategories();
    }
    @PostMapping
    public HospitalCategory createHospitalCategory(@RequestBody HospitalCategoryDto hospitalCategoryDto) {
        return hospitalCategoryService.createNewHospitalCategory(hospitalCategoryDto);
    }
}
