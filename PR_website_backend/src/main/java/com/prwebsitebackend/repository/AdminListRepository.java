package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.AdminList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminListRepository extends JpaRepository<AdminList, Long> {
}
