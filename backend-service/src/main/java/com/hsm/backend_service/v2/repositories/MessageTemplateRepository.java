package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, UUID> {
    public List<MessageTemplate> findAllByHospital(Hospital hospital);
}
