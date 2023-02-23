package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.payload.AccountsStatisticsPayload;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    GroupService groupService;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    UserServiceImpl userServiceImpl;


    public AccountsStatisticsPayload getAccountsStatistics() {
        Long totalAccounts = groupService.getAllGroupsRegistered();
        Long totalHospitals = hospitalService.getTotalHospitalsRegistered();
        Long totalUsers = userServiceImpl.getNumberOfUsersRegistered();
        AccountsStatisticsPayload payload =
                new AccountsStatisticsPayload(totalAccounts, totalHospitals, totalUsers);
        return payload;
    }


}
