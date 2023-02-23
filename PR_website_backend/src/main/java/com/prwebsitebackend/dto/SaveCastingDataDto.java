package com.prwebsitebackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;
@Data
public class SaveCastingDataDto {
    @NotBlank
    private String formId;
    @NotNull
    private Map<Long,String> data;
}
