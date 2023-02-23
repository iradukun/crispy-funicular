package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.fileHandling.File;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.enums.ERole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IUserService {

    public List<User> getAll();

    public Page<User> getAll(Pageable pageable);

    public User getById(UUID id);

    public User create(User user);

    public User update(UUID id, User user);

    public boolean delete(UUID id);

    public List<User> searchUser(String searchKey);

    public Page<User> searchUser(Pageable pageable, String searchKey);

    public User getLoggedInUser();

    public User getByEmail(String email);

    public User changeStatus(UUID id, EUserStatus status);

    public User changeProfileImage(UUID id, File file);
    public User getUserByEmailOrMobile(String email, String mobile);

    List<User> getHospitalUsers(UUID hospitalId);
    public Long getNumberOfUsersRegistered();
}
