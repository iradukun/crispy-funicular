package com.prwebsitebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prwebsitebackend.model.LoginLogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginLogsRepository extends JpaRepository<LoginLogs, Long> {
    LoginLogs findFirstByUserIdOrderByLoginDateDesc(@Param("userId") Long userId);
}
