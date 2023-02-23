package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.ScheduleManagerDto;
import com.hcm.backend_service.v2.models.HospitalAdmin;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.models.ScheduleManager;
import com.hcm.backend_service.v2.services.ScheduleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/schedule-manager")
public class ScheduleManagerController {
    @Autowired
    ScheduleManagerService service;
    @PostMapping
    public User createScheduleManager(
            @RequestBody ScheduleManagerDto dto
            ) {
        return service.createScheduleManager(dto);
    }
}
