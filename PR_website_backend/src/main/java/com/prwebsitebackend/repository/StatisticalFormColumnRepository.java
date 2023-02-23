package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.StatisticalFormColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticalFormColumnRepository extends JpaRepository<StatisticalFormColumn, Long> {
}

