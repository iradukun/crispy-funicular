package com.hcm.backend_service.v2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsStatisticsPayload {
    private Long totalAccounts;
    private Long totalHospitals;
    private Long totalUsers;
}
