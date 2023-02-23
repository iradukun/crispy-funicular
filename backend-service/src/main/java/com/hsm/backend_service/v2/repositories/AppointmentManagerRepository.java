package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.AppointmentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentManagerRepository extends IUserRepository {
}
