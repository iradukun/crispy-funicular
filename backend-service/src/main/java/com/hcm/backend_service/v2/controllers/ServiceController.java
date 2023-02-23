package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.AddServiceToGroupDto;
import com.hcm.backend_service.v2.dtos.AddServiceToHospitalDto;
import com.hcm.backend_service.v2.models.GroupAdmin;
import com.hcm.backend_service.v2.models.Service;
import com.hcm.backend_service.v2.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/services")
public class ServiceController {
    @Autowired
    ServiceService serviceService;
    @PostMapping
    public Service createService(@RequestBody Service service) {
        return serviceService.createService(service);
    }

    @GetMapping
    public List<Service> getAllServices() {return serviceService.getAllServices();}

    @GetMapping("/hospital/{hospitalId}")
    public List<Service> getHospitalServices(@PathVariable("hospitalId") UUID hospitalId) {
        return serviceService.getHospitalServices(hospitalId);
    }
    @PostMapping("/hospital/{hospitalId}")
    public List<Service> addServiceToHospital(
            @PathVariable("hospitalId") UUID hospitalId,
            @RequestBody AddServiceToHospitalDto dto
    ) {
        return serviceService.addServiceToHospital(hospitalId, dto);
    }
    @PostMapping("/group/add-service")
    public List<Service> addServiceToGroup(
            @RequestBody AddServiceToGroupDto service
    ) {
        return serviceService.addServiceToGroup(service);
    }

    //get group services
    @GetMapping("/group/{groupId}")
    public List<Service> getGroupServices(@PathVariable("groupId") UUID groupId) {
        return serviceService.getGroupServices(groupId);
    }
    @DeleteMapping("/hospital/{hospitalId}/remove/{serviceId}")
    public Service removeServiceFromHospital(
            @PathVariable("hospitalId") UUID hospitalId,
            @PathVariable("serviceId") UUID serviceId
    ) {
        return serviceService.removeServiceFromHospital(hospitalId, serviceId);
    }
    @DeleteMapping("/hospital/{hospitalId}/remove-all")
    public String removeAllServicesFromHospital(
            @PathVariable("hospitalId") UUID hospitalId
    ){
        return serviceService.removeAllServicesFromHospital(hospitalId);
    }
}
