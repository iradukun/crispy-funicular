package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.BulletPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BulletPostRepository extends JpaRepository<BulletPost, Long> {
    List<BulletPost> findByBulletinId(Long id);
}
