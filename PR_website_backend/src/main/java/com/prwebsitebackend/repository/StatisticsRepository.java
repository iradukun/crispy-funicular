package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.StatisticalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticalForm,Long> {
}
