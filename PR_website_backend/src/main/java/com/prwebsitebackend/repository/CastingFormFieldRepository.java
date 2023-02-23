package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.CastingFormField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastingFormFieldRepository extends JpaRepository<CastingFormField,Long> {
}
