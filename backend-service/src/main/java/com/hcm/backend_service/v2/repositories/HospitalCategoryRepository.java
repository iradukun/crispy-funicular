package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.HospitalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalCategoryRepository extends JpaRepository<HospitalCategory, UUID> {
}
