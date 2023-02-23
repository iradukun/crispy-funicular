package com.prwebsitebackend.dto;

import com.prwebsitebackend.enums.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastingFormFieldDto {
    private Long id;
    private String name;
    private FieldType type;
    private List<CastingFormDataDto> values;
}
