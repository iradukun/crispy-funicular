package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.ScheduleManagerDto;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.models.*;
import com.hcm.backend_service.v2.repositories.ScheduleManagerRepositoryI;
import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleManagerService {
    @Autowired
    RoleService roleService;
    @Autowired
    ScheduleManagerRepositoryI scheduleManagerRepository;
    @Autowired
    ScheduleManagerRepositoryI repository;
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    HospitalService hospitalService;
    @Qualifier("bCryptPasswordEncoder")
    @Autowired
    PasswordEncoder encoder;
    @Transactional
    public User createScheduleManager(ScheduleManagerDto dto) {
        ScheduleManager scheduleManager =
                new ScheduleManager(dto.getEmail(), dto.getMobile(), EGender.valueOf(dto.getGender()), dto.getFullName(), EUserStatus.PENDING, null, dto.getPassword(), dto.getConfirmPassword());

        Hospital hospital =
                hospitalService.getHospitalById(dto.getHospitalId());
        if(hospital == null) {
            throw new ElementNotFoundException("hospital not found");
        }
        scheduleManager.setHospital(hospital);
        scheduleManager.setPassword(encoder.encode(scheduleManager.getPassword()));
        User saved = saveScheduleManager(scheduleManager);
        return saved;
    }
    @Transactional
    public User saveScheduleManager(ScheduleManager newScheduleManager) {
        Role role = roleService.findRoleByName(ERole.SCHEDULE_MANAGER);
        newScheduleManager.setRole(role);
        ScheduleManager saved = scheduleManagerRepository.save(newScheduleManager);
        return saved;
    }
    public User getSchedulerById(UUID scheduler_id) {
        return scheduleManagerRepository.findById(scheduler_id).get();
    }
}
