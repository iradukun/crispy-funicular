package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.ScheduleStatus;
import com.hcm.backend_service.v2.enums.EScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleStatusRepository extends JpaRepository<ScheduleStatus, UUID> {
    public ScheduleStatus findByScheduleStatus(EScheduleStatus statusEnum);
}
