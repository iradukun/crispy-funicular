package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.DoctorHospitalServices;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorHospitalServicesRepository extends JpaRepository<DoctorHospitalServices, UUID> {
    public DoctorHospitalServices findDoctorHospitalServicesByDoctorAndHospital(User doctor, Hospital hospital);
}
