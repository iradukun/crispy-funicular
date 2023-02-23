package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.AppointmentManagerDto;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.exceptions.ElementAlreadyExistException;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.exceptions.EmailAlreadyExistsException;
import com.hcm.backend_service.v2.models.*;
import com.hcm.backend_service.v2.repositories.AppointmentManagerRepository;
import com.hcm.backend_service.v2.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentManagerService {
    @Qualifier("bCryptPasswordEncoder")
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    RoleService roleService;
    @Autowired
    AppointmentManagerRepository appointmentManagerRepository;
    public User saveUser(AppointmentManagerDto dto) {
        try{
            Role role = roleService.findRoleByName(ERole.APPOINTMENT_MANAGER);
            AppointmentManager newAppointmentManager =
                    new AppointmentManager(dto.getEmail(), dto.getMobile(), EGender.valueOf(dto.getGender()), dto.getFullName(), EUserStatus.PENDING,null , dto.getPassword(), dto.getConfirmPassword());
            Hospital hospital =
                    hospitalService.getHospitalById(dto.getHospitalId());
            newAppointmentManager.setPassword(encoder.encode(newAppointmentManager.getPassword()));
            newAppointmentManager.setRole(role);
            newAppointmentManager.setHospital(hospital);
            return appointmentManagerRepository.save(newAppointmentManager);
        }
        catch (Exception e) {
            if(e instanceof SQLIntegrityConstraintViolationException) {
                throw new ElementAlreadyExistException("doctor with same email or username already exist");
            }
            throw new EmailAlreadyExistsException("Email '" + dto.getEmail() + "' already exists");
        }
    }

    @Transactional
    public User saveAppointmentManager(User newUser, UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        Role role =
                roleService.findRoleByName(ERole.APPOINTMENT_MANAGER);
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        AppointmentManager newAppointmentManager =
                new AppointmentManager(newUser.getEmail(),newUser.getMobile(), newUser.getFullName(), role, newUser.getPassword(), newUser.getConfirmPassword(), hospital);
        return appointmentManagerRepository.save(newAppointmentManager);
    }

    public User getAppointmentManagerById(UUID user_id) {
        Optional<User> appointmentManager =
                appointmentManagerRepository.findById(user_id);
        if(!appointmentManager.isPresent()) {
            throw new ElementNotFoundException("appointment manager not found");
        }
        return appointmentManager.get();
    }

}
