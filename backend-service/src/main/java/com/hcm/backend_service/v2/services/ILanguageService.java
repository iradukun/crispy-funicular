package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.models.Language;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ILanguageService {

    public Language createLanguage(Language language);

    public List<Language> getAll();

    public Language update(UUID language_id, Language language);

    public Language getById(UUID language_id);
}
