package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.TransferDto;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.Transfer;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.TransferRepository;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransferService {
    @Autowired
    TransferRepository transferRepository;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    UserServiceImpl userService;

    public Transfer createTransfer(TransferDto transferDto) {
        Hospital from =
                hospitalService.getHospitalById(transferDto.getFromHospital());
        Hospital to =
                hospitalService.getHospitalById(transferDto.getToHospital());
        com.hcm.backend_service.v2.models.Service service =
                serviceService.getServiceById(transferDto.getService());
        User patient =
                userService.getById(transferDto.getPatient());
        Transfer transfer =
                new Transfer(from, to, service, patient, Date.valueOf(transferDto.getDate()));
        return transferRepository.save(transfer);
    }

    public List<Transfer> getAllTransfersFromHospital(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        return transferRepository.getTransfersByFrom(hospital);
    }
    public List<Transfer> getAllTransfersToHospital(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        return transferRepository.getTransfersByTo(hospital);
    }
}
