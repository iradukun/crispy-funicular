package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.models.ServiceCost;
import com.hcm.backend_service.v2.services.ServiceCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/service-cost")
public class ServiceCostController {
    @Autowired
    ServiceCostService serviceCostService;
    @GetMapping
    public List<ServiceCost> getAllServiceCosts() {
        return serviceCostService.getAllServiceCosts();
    }
    @GetMapping("/hospital/{hospitalId}")
    public List<ServiceCost> getHospitalServiceCosts(
            @PathVariable("hospitalId") UUID hospitalId
            ) {
        return serviceCostService.getHospitalServiceCosts(hospitalId);
    }
}
