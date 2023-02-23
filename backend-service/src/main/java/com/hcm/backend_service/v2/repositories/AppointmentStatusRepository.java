package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.AppointmentStatus;
import com.hcm.backend_service.v2.enums.EAppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, UUID> {
    public AppointmentStatus findByAppointmentStatus(EAppointmentStatus statusEnum);
}
