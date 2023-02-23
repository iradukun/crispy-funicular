package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.AddServiceToGroupDto;
import com.hcm.backend_service.v2.dtos.AddServiceToHospitalDto;
import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.exceptions.ForbiddenException;
import com.hcm.backend_service.v2.models.*;
import com.hcm.backend_service.v2.repositories.ServiceRepository;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    GroupService groupService;

    @Autowired
    BookingFeeService bookingFeeService;
    @Autowired
    UserServiceImpl userService;
    public com.hcm.backend_service.v2.models.Service createService(com.hcm.backend_service.v2.models.Service service) {
        return serviceRepository.save(service);
    }
    public com.hcm.backend_service.v2.models.Service getServiceById(UUID service_id) {
        Optional<com.hcm.backend_service.v2.models.Service> serviceOptional = serviceRepository.findById(service_id);
        if(!serviceOptional.isPresent()) {
            throw new ElementNotFoundException("service not found");
        }
        return serviceOptional.get();
    }
    public List<com.hcm.backend_service.v2.models.Service> getServicesByIds(List<UUID> ids) {
        return serviceRepository.findByService_idIn(ids);
    }

    public List<com.hcm.backend_service.v2.models.Service> getHospitalServices(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        return hospital.getServices();
    }

    public List<com.hcm.backend_service.v2.models.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public com.hcm.backend_service.v2.models.Service getServiceByServiceName(String serviceName) {
        com.hcm.backend_service.v2.models.Service service =
                serviceRepository.findByService(serviceName);
        return service;
    }
    @Transactional
    public List<com.hcm.backend_service.v2.models.Service> addServiceToHospital(UUID hospitalId, AddServiceToHospitalDto dto) {
        BookingFee fee =
                new BookingFee(dto.getFee(), dto.getCurrency());
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        fee.setHospital(hospital);
        Group group = hospital.getGroup();
        com.hcm.backend_service.v2.models.Service service =
                getServiceById(dto.getServiceId());
        fee.setService(service);
        if(!group.getServices().contains(service)) {
            throw new BadRequestException("service does not belong to the group");
        }
        if(!group.getHospitals().contains(hospital)) {
            throw new BadRequestException("hospital does not belong to the group");
        }
        hospital.getServices().add(service);
        hospitalService.updateHospital(hospital);
        bookingFeeService.createBookingFee(fee);
        return hospital.getServices();
    }

    public List<com.hcm.backend_service.v2.models.Service> addServiceToGroup(AddServiceToGroupDto service) {
        User groupAdmin =
                userService.getLoggedInUser();
        if(groupAdmin instanceof GroupAdmin) {
            com.hcm.backend_service.v2.models.Service dbService =
                    getServiceByServiceName(service.getService());
            if(dbService == null) {
                dbService = serviceRepository.save(new com.hcm.backend_service.v2.models.Service(service.getService()));
            }
            Group group =
                    ((GroupAdmin) groupAdmin).getGroup();
            group.getServices().add(dbService);
            groupService.createGroup(groupAdmin, group);
            return group.getServices();
        }else{
            throw new ForbiddenException("Not allowed");
        }
    }

    public List<com.hcm.backend_service.v2.models.Service> getGroupServices(UUID groupId) {
        Group group =
                groupService.getGroupById(groupId);
        return group.getServices();
    }

    public com.hcm.backend_service.v2.models.Service removeServiceFromHospital(UUID hospitalId, UUID serviceId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        com.hcm.backend_service.v2.models.Service service =
                getServiceById(serviceId);
        hospital.getSchedules().remove(service);
        hospitalService.updateHospital(hospital);
        return service;
    }

    public String removeAllServicesFromHospital(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        hospital.getServices().clear();
        hospitalService.updateHospital(hospital);
        return "removed successfully";
    }
}
