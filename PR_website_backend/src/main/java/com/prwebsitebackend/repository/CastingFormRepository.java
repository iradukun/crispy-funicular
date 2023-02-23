package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.CastingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastingFormRepository extends JpaRepository<CastingForm, Long> {
}
