package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.repositories.IRoleRepository;
import com.hcm.backend_service.v2.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    IRoleRepository IRoleRepository;
    public Role createRole(Role role) {
        return IRoleRepository.save(role);
    }
    public Role findRoleByName(ERole roleName) {
        Role role = IRoleRepository.findByRole(roleName);
        if(role == null) {
            throw new BadRequestException("role not found");
        }
        return role;
    }

    public List<Role> getAllRoles() {
        return IRoleRepository.findAll();
    }
}
