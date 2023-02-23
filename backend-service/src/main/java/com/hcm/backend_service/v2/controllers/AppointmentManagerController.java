package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.AppointmentManagerDto;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.services.AppointmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/appointment-manager")
public class AppointmentManagerController {
    @Autowired
    AppointmentManagerService appointmentManagerService;
    @PostMapping
    public User createAppointmentManager(@RequestBody AppointmentManagerDto dto) {
        return appointmentManagerService.saveUser(dto);
    }
}
