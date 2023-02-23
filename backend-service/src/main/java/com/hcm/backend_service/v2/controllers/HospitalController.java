package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.payload.HospitalPayload;
import com.hcm.backend_service.v2.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/hospitals")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;
    @PostMapping
    public Hospital createNewHospital(@RequestBody HospitalPayload hospitalPayload) {

        return hospitalService.createNewHospital(hospitalPayload);
    }
    @GetMapping
    public List<Hospital> getAllHospitals(){
        return hospitalService.getAllHospitals();
    }
    @GetMapping("/group/{groupId}")
    public List<Hospital> getGroupHospitals(@PathVariable("groupId") UUID groupId) {
        return hospitalService.getGroupHospitals(groupId);
    }
    @PutMapping("/{hospitalId}")
    public Hospital editHospital(
            @PathVariable("hospitalId") UUID hospitalId,
            @RequestBody HospitalPayload hospitalPayload
    ) {
        return hospitalService.editHospital(hospitalPayload, hospitalId);
    }
    @DeleteMapping("/{hospitalI}")
    public String deleteHospital(
            @PathVariable("hospitalId") UUID hospitalId
    ) {
        return hospitalService.deleteHospital(hospitalId);
    }

}
