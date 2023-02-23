package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.exceptions.ElementAlreadyExistException;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.exceptions.EmailAlreadyExistsException;
import com.hcm.backend_service.v2.models.Doctor;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.DoctorRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    @Autowired
    DoctorRepositoryI doctorRepository;

    @Autowired
    IUserService IUserService;

    @Autowired
    RoleService roleService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    DoctorHospitalServicesService doctorHospitalServicesService;

    @Qualifier("bCryptPasswordEncoder")
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    HospitalService hospitalService;
    public User saveUser(User newUser, List<UUID> services, UUID hospitalId) {
        try{
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            User savedDoctor = saveDoctor(newUser, services, hospitalId);
            System.out.println(savedDoctor);
            return savedDoctor;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            if(e instanceof SQLIntegrityConstraintViolationException) {
                throw new ElementAlreadyExistException("doctor with same email or username already exist");
            }
            throw new EmailAlreadyExistsException("An error occurred. Try again in 5 minutes!");
        }
    }

    public User getDoctorById(UUID doctor_id) {
        User doctor = doctorRepository.findById(doctor_id).get();
        if(doctor == null) {
            throw new Error("doctor not found");
        }
        return doctor;
    }
    @Transactional
    public User saveDoctor(User newUser, List<UUID> services, UUID hospitalId) {
        if(hospitalId.toString() == null) {
            throw new BadRequestException("Doctor should be working in a hospital");
        }

        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        if(hospital == null) {
            throw new ElementNotFoundException("No hospital found");
        }

        List<com.hcm.backend_service.v2.models.Service> doctorServices =
                serviceService.getServicesByIds(services);
        System.out.println(doctorServices);
        User doctor =
                new Doctor(newUser.getEmail(), newUser.getFullName(), newUser.getPassword(), newUser.getConfirmPassword(), newUser.getGender(), newUser.getMobile(), newUser.getRole(), doctorServices, hospital);

        User saved = doctorRepository.save(doctor);

        doctorHospitalServicesService.createDocHospService(hospital, (Doctor) saved, doctorServices);
        return saved;
    }


    public String deleteDoctor(UUID doctorId) {
        doctorRepository.deleteById(doctorId);
        return "deleted successfully";
    }

}
