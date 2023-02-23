package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.models.Doctor;
import com.hcm.backend_service.v2.models.DoctorHospitalServices;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.DoctorHospitalServicesRepository;
import com.hcm.backend_service.v2.repositories.DoctorRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorHospitalServicesService {
    @Autowired
    DoctorHospitalServicesRepository doctorHospitalServicesRepository;

    @Autowired
    DoctorRepositoryI doctorRepository;

    @Autowired
    HospitalService hospitalService;
    public List<com.hcm.backend_service.v2.models.Service> getDoctorServicesInHospital(UUID doctorId, UUID hospitalId) {
        User doctor = doctorRepository.findById(doctorId).get();
        if(doctor == null) {
            throw new Error("doctor not found");
        }

        if(doctor instanceof Doctor) {
            Hospital hospital =
                    hospitalService.getHospitalById(hospitalId);
            DoctorHospitalServices doctorHospitalServices =
                    doctorHospitalServicesRepository.findDoctorHospitalServicesByDoctorAndHospital(doctor, hospital);
            return doctorHospitalServices.getDoctorServices();

        }else{
            throw new BadRequestException("user is not a doctor");
        }
    }

    public DoctorHospitalServices createDocHospService(Hospital hospitalId, Doctor doctor, List<com.hcm.backend_service.v2.models.Service> doctorServices) {
        DoctorHospitalServices doctorHospitalServices = new DoctorHospitalServices();
        doctorHospitalServices.setHospital(hospitalId);
        doctorHospitalServices.setDoctor(doctor);
        doctorHospitalServices.setDoctorServices(doctorServices);
        DoctorHospitalServices doctorHospitalServiceS = doctorHospitalServicesRepository.save(doctorHospitalServices);
        return doctorHospitalServices;
    }
}
