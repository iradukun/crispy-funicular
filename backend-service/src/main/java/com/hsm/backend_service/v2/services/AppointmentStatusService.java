package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.enums.EAppointmentStatus;
import com.hcm.backend_service.v2.models.AppointmentStatus;
import com.hcm.backend_service.v2.repositories.AppointmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppointmentStatusService {
    @Autowired
    AppointmentStatusRepository repository;
    public AppointmentStatus getStatusById(UUID status_id) {
        AppointmentStatus status =
                repository.findById(status_id).get();
        if(status == null) {
            throw new Error("status not available");
        }
        return status;
    }
    public AppointmentStatus getStatusByName(EAppointmentStatus statusEnum) {
        AppointmentStatus status =
                repository.findByAppointmentStatus(statusEnum);
        return status;
    }
    public AppointmentStatus createAppointmentStatus(AppointmentStatus status) {
        return repository.save(status);
    }
}
