package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.ConnectionAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamAccountRepository extends JpaRepository<ConnectionAccount, Long> {
}
