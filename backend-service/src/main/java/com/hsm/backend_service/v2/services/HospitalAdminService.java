package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.HospitalAdminDto;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.models.*;
import com.hcm.backend_service.v2.repositories.HospitalAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HospitalAdminService {

    @Autowired

    RoleService roleService;
    @Autowired
    HospitalAdminRepository hospitalAdminRepository;

    @Autowired
    HospitalService hospitalService;
    @Qualifier("bCryptPasswordEncoder")
    @Autowired
    BCryptPasswordEncoder encoder;
    public User createHospitalAdmin(HospitalAdminDto dto) {
        Role role = roleService.findRoleByName(ERole.HOSPITAL_ADMIN);
        HospitalAdmin newHospitalAdmin =
                new HospitalAdmin(dto.getEmail(), dto.getMobile(), EGender.valueOf(dto.getGender()), dto.getFullName(), EUserStatus.PENDING, null, dto.getPassword(), dto.getConfirmPassword());
        Hospital hospital =
                hospitalService.getHospitalById(dto.getHospitalId());
        newHospitalAdmin.setPassword(encoder.encode(newHospitalAdmin.getPassword()));
        newHospitalAdmin.setRole(role);
        newHospitalAdmin.setHospital(hospital);
        return hospitalAdminRepository.save(newHospitalAdmin);
    }
    public HospitalAdmin getHospitalAdminById(UUID hospitalAdminId) {
        Optional<User> hospitalAdmin = hospitalAdminRepository.findById(hospitalAdminId);
        if(!hospitalAdmin.isPresent()) {
            throw new ElementNotFoundException("hospital admin not found");
        }
        if(hospitalAdmin.get() instanceof HospitalAdmin) {
            return (HospitalAdmin) hospitalAdmin.get();
        }else {
            return null;
        }
    }

    public List<HospitalAdmin> getHospitalAdminForHospital(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        return hospital.getHospitalAdmins();
    }

    public User editHospitalAdmin(HospitalAdminDto dto, UUID hospitalAdminId) {
        HospitalAdmin hospitalAdmin =
                getHospitalAdminById(hospitalAdminId);
        Hospital hospital =
                hospitalService.getHospitalById(dto.getHospitalId());
        hospitalAdmin.setHospital(hospital);
        return hospitalAdminRepository.save(hospitalAdmin);
    }
}
