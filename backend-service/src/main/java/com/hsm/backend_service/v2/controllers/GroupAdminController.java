package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.SignUpDTO;
import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.services.GroupAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/group-admin")
public class GroupAdminController {
    @Autowired
    GroupAdminService groupAdminService;

    @GetMapping
    public List<User> getAllGroupAdmins() {
        return groupAdminService.getAllGroupAdmin();
    }
    @PostMapping("/{groupId}")
    public User createGroupAdmin(
            @RequestBody SignUpDTO groupAdmin,
            @PathVariable UUID groupId
            ) {
        if (!groupAdmin.getPassword().equals(groupAdmin.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        return groupAdminService.createGroupAdmin(groupAdmin, groupId);
    }
}
