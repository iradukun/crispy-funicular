package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.Backup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupRepository extends JpaRepository<Backup, Long> {
}
