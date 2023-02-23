package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.ManagementPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementPermissionsRepository extends JpaRepository<ManagementPermissions, Long> {

}

