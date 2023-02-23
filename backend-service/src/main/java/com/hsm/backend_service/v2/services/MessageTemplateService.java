package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.MessageTemplateDto;
import com.hcm.backend_service.v2.enums.EMessageTemplateType;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.MessageTemplate;
import com.hcm.backend_service.v2.repositories.MessageTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageTemplateService {

    @Autowired
    MessageTemplateRepository messageTemplateRepository;
    @Autowired
    HospitalService hospitalService;
    public List<MessageTemplate> getMessageTemplates() {
        return messageTemplateRepository.findAll();
    }

    public List<MessageTemplate> getHospitalMessageTemplates(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        return messageTemplateRepository.findAllByHospital(hospital);
    }

    public MessageTemplate createMessageTemplate(UUID hospitalId, MessageTemplateDto dto) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        MessageTemplate messageTemplate = new MessageTemplate(EMessageTemplateType.valueOf(dto.getType()), dto.getTitle(), dto.getMessageBody());
        messageTemplate.setHospital(hospital);
        return messageTemplateRepository.save(messageTemplate);
    }
}
