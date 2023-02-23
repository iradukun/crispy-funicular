package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.models.Service;
import com.hcm.backend_service.v2.services.DoctorHospitalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/doctor-hospital-services")
public class DoctorHospitalServicesController {

    @Autowired
    DoctorHospitalServicesService doctorHospitalServicesService;

    @GetMapping("/get-doctor-hospital-services/{doctorId}/{hospitalId}")
    public List<Service> getDoctorServicesInHospital(
            @PathVariable("doctorId") UUID doctorId,
            @PathVariable("hospitalId") UUID hospitalId
            ) {
        return doctorHospitalServicesService.getDoctorServicesInHospital(doctorId, hospitalId);
    }
}
