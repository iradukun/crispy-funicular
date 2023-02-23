package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.CastingReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CastingReportRepository extends JpaRepository<CastingReport, Long> {
}
