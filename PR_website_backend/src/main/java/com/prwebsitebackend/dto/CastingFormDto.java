package com.prwebsitebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastingFormDto {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private List<CastingFormFieldDto> fields;
    private LocalDateTime createdAt;
}
