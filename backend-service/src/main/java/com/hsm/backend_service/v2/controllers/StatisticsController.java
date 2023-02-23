package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.payload.AccountsStatisticsPayload;
import com.hcm.backend_service.v2.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v2/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/accounts")
    public AccountsStatisticsPayload getAccountsStatistics() {
        return statisticsService.getAccountsStatistics();
    }
}
