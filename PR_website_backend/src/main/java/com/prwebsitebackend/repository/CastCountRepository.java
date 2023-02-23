package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.CastCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CastCountRepository extends JpaRepository<CastCount,Long> {
    @Query(value="SELECT * from cast_count where cast_count.schedule_id= ?1",nativeQuery = true)
    CastCount findCastCountBySchedule(Long scheduleId);
}
