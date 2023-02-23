package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
    List<Hospital> findByHospitalIdIn(List<UUID> ids);
    @Query(value = "SELECT count(*) FROM hospital", nativeQuery = true)
    public Long countAllHospitals();
}
