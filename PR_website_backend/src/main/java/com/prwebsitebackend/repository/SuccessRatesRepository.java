package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.SuccessRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessRatesRepository extends JpaRepository<SuccessRates, Long> {
}
