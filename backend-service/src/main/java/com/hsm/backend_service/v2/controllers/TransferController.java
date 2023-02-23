package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.TransferDto;
import com.hcm.backend_service.v2.models.Transfer;
import com.hcm.backend_service.v2.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/transfers")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping
    public Transfer createTransfer(
            @RequestBody TransferDto transferDto
    ) {
        return transferService.createTransfer(transferDto);
    }
    @GetMapping("/from/{hospitalId}")
    public List<Transfer> getAllTransfersFromHospital(
            @PathVariable("hospitalId") UUID hospitalId
    ) {
        return transferService.getAllTransfersFromHospital(hospitalId);
    }
    @GetMapping("/to/{hospitalId}")
    public List<Transfer> getAllTransfersToHospital(
            @PathVariable("hospitalId") UUID hospitalId
    ) {
        return transferService.getAllTransfersToHospital(hospitalId);
    }
}
