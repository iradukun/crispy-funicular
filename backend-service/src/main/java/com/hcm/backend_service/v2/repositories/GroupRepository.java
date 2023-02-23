package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    @Query(value = "SELECT COUNT(*) FROM \"group\"", nativeQuery = true)
    public Long countAllGroups();
}
