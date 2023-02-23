package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Appointment;
import com.hcm.backend_service.v2.enums.EAppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query(value = "UPDATE appointment set ", nativeQuery = true)
    void changeStatus(UUID schedule_id, EAppointmentStatus accepted);
}
