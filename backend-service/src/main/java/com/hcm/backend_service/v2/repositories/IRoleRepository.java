package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    public Role findByRole(ERole role);
}
