package com.hcm.backend_service.v2.serviceImpls;

import com.hcm.backend_service.v2.exceptions.ResourceNotFoundException;
import com.hcm.backend_service.v2.models.Language;
import com.hcm.backend_service.v2.repositories.ILanguageRepository;
import com.hcm.backend_service.v2.services.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LanguageServiceImpl implements ILanguageService {
    @Autowired
    ILanguageRepository languageRepository;

    @Override
    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public List<Language> getAll() {
        return this.languageRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public Language update(UUID language_id, Language language) {
        Language language_update =
                getById(language_id);
        language_update.setLanguage_name(language.getLanguage_name());
        language_update.setLanguage_standard_code(language.getLanguage_standard_code());
        language_update.setLanguage_description(language.getLanguage_description());
        return languageRepository.save(language_update);
    }

    @Override
    public Language getById(UUID language_id) {
        return this.languageRepository.findById(language_id).orElseThrow(
                () -> new ResourceNotFoundException("Language", "id", language_id.toString()));
    }
}
