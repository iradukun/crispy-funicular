package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.enums.EFileStatus;
import com.hcm.backend_service.v2.fileHandling.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFileRepository extends JpaRepository<File, UUID> {
    Page<File> findAllByStatus(Pageable pageable, EFileStatus status);

}
