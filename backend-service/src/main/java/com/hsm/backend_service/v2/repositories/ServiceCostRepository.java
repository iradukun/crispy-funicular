package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.ServiceCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceCostRepository extends JpaRepository<ServiceCost, UUID> {
    public List<ServiceCost> getAllByHospital(Hospital hospital);
}
