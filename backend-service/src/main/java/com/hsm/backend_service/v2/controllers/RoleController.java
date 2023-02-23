package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/roles")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
    @PostMapping
    public Role generateRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }
}
