package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.CastingFormDataDto;
import com.prwebsitebackend.dto.CastingFormDto;
import com.prwebsitebackend.dto.CastingFormFieldDto;
import com.prwebsitebackend.dto.SaveCastingDataDto;
import com.prwebsitebackend.exception.ResourceNotFoundException;
import com.prwebsitebackend.model.CastingForm;
import com.prwebsitebackend.model.CastingFormData;
import com.prwebsitebackend.model.CastingFormField;
import com.prwebsitebackend.repository.CastingFormFieldRepository;
import com.prwebsitebackend.repository.CastingFormDataRepository;
import com.prwebsitebackend.repository.CastingFormRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CastingService {

    private final CastingFormRepository castingFormRepository;
    private final CastingFormFieldRepository castingFormFieldRepository;
    private final CastingFormDataRepository castingFormDataRepository;

    public CastingService(CastingFormRepository castingFormRepository, CastingFormFieldRepository castingFormFieldRepository, CastingFormDataRepository castingFormDataRepository) {
        this.castingFormRepository = castingFormRepository;
        this.castingFormFieldRepository = castingFormFieldRepository;
        this.castingFormDataRepository = castingFormDataRepository;
    }


    public List<CastingForm> getAllCastingForms() {
        return castingFormRepository.findAll();
    }

    public CastingForm createCastingForm(CastingFormDto dto) {
        CastingForm cl = castingFormRepository.save(new CastingForm(dto.getName()));
        List<CastingFormField> columns = new ArrayList<>();

        for (CastingFormFieldDto column : dto.getFields()) {
            CastingFormField field = new CastingFormField();
            field.setName(column.getName());
            field.setType(column.getType());
            field.setCastingForm(cl);
            columns.add(field);
        }

        List<CastingFormField> castingFormFields = castingFormFieldRepository.saveAll(columns);
        cl.setFields(castingFormFields);
        return castingFormRepository.save(cl);
    }

    public CastingFormData SaveFormData(SaveCastingDataDto dto) {

        CastingForm saved = castingFormRepository.findById(Long.parseLong(dto.getFormId())).orElse(null);
        if(saved == null) {
            throw new ResourceNotFoundException("No casting form with given Id was found");
        }

        List<Long> ids = new ArrayList<>();
        List<CastingFormField> fields = saved.getFields();

        for(CastingFormField field : fields){
            ids.add(field.getId());
        }

        if(ids.equals(new ArrayList<>(dto.getData().keySet()))){
            return castingFormDataRepository.save(new CastingFormData(saved, dto.getData()));
        }

        return null;
    }

    public Set<CastingFormData> getCastingData(String formId){
        CastingForm saved = castingFormRepository.findById(Long.parseLong(formId)).orElse(null);
        List<CastingFormData> data = castingFormDataRepository.findAllWithFieldValuesByFormId(saved);
        if(data == null){
            throw new ResourceNotFoundException("No casting data with given formId was found");
        }
        return new HashSet<>(data);
    }


    public CastingForm getCastingById(Long id) {
        return castingFormRepository.findById(id).orElse(null);
    }

    public Boolean deleteCastingDataById(Long id) {
        CastingFormData castingData = castingFormDataRepository.findById(id).orElse(null);
        if(castingData == null) {
            throw new ResourceNotFoundException("No casting form with given Id was found");
        }
        castingFormDataRepository.delete(castingData);
        return true;
    }

    public CastingFormData updateCastingFormData(CastingFormDataDto dto) {
        CastingForm castingForm = castingFormRepository.findById(dto.getFormId()).orElse(null);
        if(castingForm == null) {
            throw new ResourceNotFoundException("No casting form with given Id was found");
        }
        CastingFormData castingData = castingFormDataRepository.findById(dto.getFormId()).orElse(null);
        if(castingData == null) {
            throw new ResourceNotFoundException("No casting data with given Id was found");
        }
        castingData.setFieldValue(dto.getFieldValue());
        return castingFormDataRepository.save(castingData);
    }

}
