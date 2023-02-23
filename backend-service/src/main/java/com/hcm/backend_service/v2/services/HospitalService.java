package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.models.*;
import com.hcm.backend_service.v2.payload.HospitalPayload;
import com.hcm.backend_service.v2.repositories.HospitalRepository;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    HospitalCategoryService hospitalCategoryService;
    @Autowired
    GroupAdminService groupAdminService;
    @Autowired
    GroupService groupService;

    @Autowired
    UserServiceImpl userServiceImpl;

    public Hospital getHospitalById(UUID hospitalId) {
        Optional<Hospital> hospitalOptional =
                hospitalRepository.findById(hospitalId);
        if(!hospitalOptional.isPresent()) {
            throw new ElementNotFoundException("hospital not found");
        }
        return hospitalOptional.get();
    }

    @Transactional
    public Hospital createNewHospital(HospitalPayload hospitalPayload) {
        User loggedInUser = userServiceImpl.getLoggedInUser();
        User grpAdmin =
                groupAdminService.getGroupAdminById(loggedInUser.getId());
        if(grpAdmin instanceof GroupAdmin) {
            HospitalCategory hospitalCategory =
                    hospitalCategoryService.getHospitalCategoryById(hospitalPayload.getHospitalCategoryId());
            Hospital newHospital = new Hospital(
                    hospitalPayload.getHospitalName(),
                    hospitalPayload.getEmail(),
                    hospitalPayload.getLocation()
            );
            newHospital.setGroup(((GroupAdmin) grpAdmin).getGroup());
            newHospital.setHospitalCategory(hospitalCategory);
            return hospitalRepository.save(newHospital);
        }else{
            throw new BadRequestException("user not group admin");
        }
    }

    public List<Hospital> getHospitalsByIds(List<UUID> hospitalIds) {
        return hospitalRepository.findByHospitalIdIn(hospitalIds);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public List<Hospital> getGroupHospitals(UUID groupId) {
        Group group =
                groupService.getGroupById(groupId);
        return group.getHospitals();
    }

    public Hospital updateHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Hospital editHospital(HospitalPayload hospitalPayload, UUID hospitalId) {
        Hospital hospital =
                getHospitalById(hospitalId);
        HospitalCategory hospitalCategory =
                hospitalCategoryService.getHospitalCategoryById(hospitalPayload.getHospitalCategoryId());
        hospital.setHospitalCategory(hospitalCategory);
        hospital.setHospitalName(hospitalPayload.getHospitalName());
        hospital.setEmail(hospitalPayload.getEmail());
        hospital.setLocation(hospitalPayload.getLocation());
        return hospitalRepository.save(hospital);
    }

    public String deleteHospital(UUID hospitalId) {
        try {
            hospitalRepository.deleteById(hospitalId);
            return "deleted successfully";
        }catch (Exception ex) {
            throw new BadRequestException("unable to delete hospital");
        }
    }
    public Long getTotalHospitalsRegistered() {
        return hospitalRepository.countAllHospitals();
    }
}
