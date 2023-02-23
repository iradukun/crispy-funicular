package com.hcm.backend_service.v2.payload;

import com.hcm.backend_service.v2.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPayload {
    @NotNull(
            message = "doctor details are required"
    )
    private User user;

    @NotNull(message = "doctor needs at least one service")
    private List<UUID> services;
    private List<UUID> hospitals;

}
