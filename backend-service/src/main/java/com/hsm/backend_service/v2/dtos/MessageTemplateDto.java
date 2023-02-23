package com.hcm.backend_service.v2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageTemplateDto {
    private String type;
    private String title;
    private String messageBody;
}
