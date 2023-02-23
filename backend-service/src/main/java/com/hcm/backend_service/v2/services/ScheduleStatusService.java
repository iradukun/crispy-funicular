package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.models.ScheduleStatus;
import com.hcm.backend_service.v2.repositories.ScheduleStatusRepository;
import com.hcm.backend_service.v2.enums.EScheduleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleStatusService {
    @Autowired
    ScheduleStatusRepository repository;
    public ScheduleStatus getStatusByName(EScheduleStatus statusEnum) {
        return repository.findByScheduleStatus(EScheduleStatus.ACTIVE);
    }
}
