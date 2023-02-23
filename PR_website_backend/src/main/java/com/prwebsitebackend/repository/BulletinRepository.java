package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {
}
