package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.ServiceCost;
import com.hcm.backend_service.v2.repositories.ServiceCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceCostService {
    @Autowired
    ServiceCostRepository serviceCostRepository;
    @Autowired
    HospitalService hospitalService;
    public List<ServiceCost> getAllServiceCosts() {
        return serviceCostRepository.findAll();
    }

    public List<ServiceCost> getHospitalServiceCosts(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        List<ServiceCost> serviceCosts =
                serviceCostRepository.getAllByHospital(hospital);
        return serviceCosts;
    }
}
