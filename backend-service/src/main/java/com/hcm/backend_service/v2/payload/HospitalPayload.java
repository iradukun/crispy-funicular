package com.hcm.backend_service.v2.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.models.Group;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.HospitalCategory;
import com.hcm.backend_service.v2.models.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalPayload {
    @NotNull(
            message = "hospital should have a category"
    )
    private UUID hospitalCategoryId;
    @NotBlank(
            message = "hospital should have a name"
    )
    private String hospitalName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Location is required.")
    private String location;

}
