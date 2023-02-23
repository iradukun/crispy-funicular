package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.SignUpDTO;
import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.services.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/super-admin")
public class SuperAdminController {
    @Autowired
    SuperAdminService superAdminService;
    @PostMapping
    public User createSuperAdmin(@RequestBody SignUpDTO superAdminDto) {
        if (!superAdminDto.getPassword().equals(superAdminDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        return superAdminService.createSuperAdmin(superAdminDto);
    }
}
