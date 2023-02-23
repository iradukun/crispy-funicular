package com.hcm.backend_service.v2.payload;

import com.hcm.backend_service.v2.enums.EMessageVia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentPayload {
    private String start_time;
    private String end_time;
}
