package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.CreateDoctorDTO;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.models.Doctor;
import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.payload.ApiResponse;
import com.hcm.backend_service.v2.services.DoctorService;
import com.hcm.backend_service.v2.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v2/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    RoleService roleService;
    @PostMapping()
    public ResponseEntity<ApiResponse> saveDoctor(@RequestBody CreateDoctorDTO doctorDTO) {

        if (!doctorDTO.getPassword().equals(doctorDTO.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        User user = new User();

        Role role = roleService.findRoleByName(ERole.DOCTOR);

        user.setEmail(doctorDTO.getEmail());
        user.setMobile(doctorDTO.getMobile());
        user.setGender(EGender.valueOf(doctorDTO.getGender()));
        user.setFullName(doctorDTO.getFullName());
        user.setPassword(doctorDTO.getPassword());
        user.setConfirmPassword(doctorDTO.getConfirmPassword());
        user.setRole(role);

        Doctor entity = (Doctor) doctorService.saveUser(user, doctorDTO.getServices(), doctorDTO.getHospitalId());

        return ResponseEntity.ok(new ApiResponse(true, entity));
    }

    @DeleteMapping("/{doctorId}")
    public String deleteDoctor(@PathVariable("doctorId") UUID doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }

}
