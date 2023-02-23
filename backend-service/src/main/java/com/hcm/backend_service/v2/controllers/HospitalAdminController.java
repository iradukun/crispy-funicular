package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.HospitalAdminDto;
import com.hcm.backend_service.v2.models.HospitalAdmin;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.services.HospitalAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/hospital-admin")
public class HospitalAdminController {
    @Autowired
    HospitalAdminService hospitalAdminService;
    @PostMapping()
    public User createHospitalAdmin(
            @RequestBody HospitalAdminDto dto
    ) {
        return hospitalAdminService.createHospitalAdmin(dto);
    }
    @GetMapping("/{hospitalId}")
    public List<HospitalAdmin> getHospitalAdmins(
            @PathVariable("hospitalId") UUID hospitalId
    ) {
        return hospitalAdminService.getHospitalAdminForHospital(hospitalId);
    }

    @PutMapping("/{userId}")
    public User editHospitalAdmin(
            @RequestBody HospitalAdminDto dto,
            @PathVariable("userId") UUID hospitalAdminId
    ) {
        return hospitalAdminService.editHospitalAdmin(dto, hospitalAdminId);
    }
}
