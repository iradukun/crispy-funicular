package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.MessageTemplateDto;
import com.hcm.backend_service.v2.models.MessageTemplate;
import com.hcm.backend_service.v2.services.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/message-template")
public class MessageTemplateController {
    @Autowired
    MessageTemplateService messageTemplateService;
    @GetMapping
    public List<MessageTemplate> getMessageTemplates() {
        return messageTemplateService.getMessageTemplates();
    }
    @GetMapping("/hospitals/{hospitalId}")
    public List<MessageTemplate> getHospitalMessageTemplates(
            @PathVariable("hospitalId") UUID hospitalId
            ) {
        return messageTemplateService.getHospitalMessageTemplates(hospitalId);
    }
    @PostMapping("/hospitals/{hospitalId}")
    public MessageTemplate createMessageTemplate(
            @PathVariable("hospitalId") UUID hospitalId,
            @RequestBody MessageTemplateDto dto
            ) {
        return messageTemplateService.createMessageTemplate(hospitalId, dto);
    }
}
