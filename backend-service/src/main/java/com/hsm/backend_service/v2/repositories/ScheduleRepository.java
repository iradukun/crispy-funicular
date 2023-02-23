package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Doctor;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.Schedule;
import com.hcm.backend_service.v2.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    public List<Schedule> findAllByHospitalAndService(Hospital hospital, Service service);
    public List<Schedule> findAllByHospitalAndDoctor(Hospital hospital, Doctor doctor);
}
