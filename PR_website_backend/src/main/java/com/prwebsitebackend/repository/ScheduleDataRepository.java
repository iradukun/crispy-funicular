package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.ScheduleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDataRepository extends JpaRepository<ScheduleData, Long> {
    List<ScheduleData> findScheduleDataByScheduleId(Long parseLong);
}
