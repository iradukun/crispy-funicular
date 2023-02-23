package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.SignUpDTO;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.fileHandling.File;
import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.models.SuperAdmin;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.SuperAdminRepositoryI;
import com.hcm.backend_service.v2.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.*;

@Service
public class SuperAdminService {
    @Autowired
    SuperAdminRepositoryI superAdminRepository;
    @Qualifier("bCryptPasswordEncoder")
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleService roleService;
    public User createSuperAdmin(SignUpDTO superAdminDto) {
        Role role = roleService.findRoleByName(ERole.SUPER_ADMIN);
        User newSuperAdmin =
                new SuperAdmin(superAdminDto.getEmail(),superAdminDto.getMobile(), EGender.valueOf(superAdminDto.getGender()), superAdminDto.getFullName(),EUserStatus.PENDING, null, superAdminDto.getPassword(), superAdminDto.getConfirmPassword());

        newSuperAdmin.setPassword(encoder.encode(superAdminDto.getPassword()));
        newSuperAdmin.setRole(role);
        return superAdminRepository.save(newSuperAdmin);
    }
    public User getSuperAdminById(UUID superAdminId) {
        Optional<User> superAdmin = superAdminRepository.findById(superAdminId);
        if(!superAdmin.isPresent()) {
            throw new ElementNotFoundException("super admin not found");
        }
        return superAdmin.get();
    }
}
