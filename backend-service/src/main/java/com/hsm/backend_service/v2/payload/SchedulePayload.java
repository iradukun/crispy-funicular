package com.hcm.backend_service.v2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulePayload {
    @NotNull(message = "doctor is required")
    private UUID doctor_id;
    @NotNull(message = "service is required")
    private UUID service_id;
    private Date start_date;
    private Date end_date;
    private Time start_time;
    private Time end_time;
}
