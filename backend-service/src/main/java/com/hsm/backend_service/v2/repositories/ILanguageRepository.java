package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ILanguageRepository extends JpaRepository<Language, UUID> {
}
