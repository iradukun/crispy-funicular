package com.prwebsitebackend.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CastingFormDataDto {
    private Long id;
    private Long formId;
    private Map<Long,String> fieldValue;
}
