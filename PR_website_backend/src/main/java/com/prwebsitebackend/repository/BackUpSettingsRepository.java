package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.BackUpSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BackUpSettingsRepository extends JpaRepository<BackUpSettings, Long> {
    @Query(value="SELECT * FROM back_up_settings b WHERE b.user_id = ?1",nativeQuery = true)
    BackUpSettings findByUserId(Long userId);
}
