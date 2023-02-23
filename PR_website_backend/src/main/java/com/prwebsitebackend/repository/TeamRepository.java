package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.Team;
import com.prwebsitebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value="SELECT * FROM team WHERE team.user_id = ?1", nativeQuery = true)
    List<Team> findAllUserTeams(Long userId);
}

