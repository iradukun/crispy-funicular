package com.hcm.backend_service.v2.repositories;

import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.models.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Repository("IUserRepository")
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID userID);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrMobile(String email, String mobile);

    List<User> findByRole(Role role);

    @Query("select u from User u where u.id = ?1")
    User getUserByUser_id(UUID id);

    @Query("SELECT u FROM User u" +
            " WHERE (lower(u.fullName)  LIKE ('%' || lower(:searchKey) || '%')) " +
            " OR (lower(u.email) LIKE ('%' || lower(:searchKey) || '%'))")
    List<User> searchUser(String searchKey);

    @Query("SELECT u FROM User u" +
            " WHERE (lower(u.fullName)  LIKE ('%' || lower(:searchKey) || '%')) " +
            " OR (lower(u.email) LIKE ('%' || lower(:searchKey) || '%'))")
    Page<User> searchUser(Pageable pageable, String searchKey);

    Page<User> findAllByRole(Pageable pageable, Role role);

    @Query(value = "SELECT count(*) FROM users", nativeQuery = true)
    public Long countAllUsers();
}
