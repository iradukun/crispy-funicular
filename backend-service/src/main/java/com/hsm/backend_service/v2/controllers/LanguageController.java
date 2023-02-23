package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.models.Language;
import com.hcm.backend_service.v2.serviceImpls.LanguageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/languages")
public class LanguageController {

    private final LanguageServiceImpl languageService;

    @Autowired
    public LanguageController(LanguageServiceImpl languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/create")
    public Language createNewLanguage(
            @RequestBody Language language
    ) {
        if(language.getLanguage_name() != null || language.getLanguage_description() != null || language.getLanguage_standard_code() != null) {
            return languageService.createLanguage(language);
        } else {
            throw new BadRequestException("All fields are required");
        }
    }

    @GetMapping("/all")
    public List<Language> getAllLanguages() {
        return languageService.getAll();
    }

    @PutMapping("/edit/{language_id}")
    public Language editLanguage(
            @PathVariable("language_id") UUID language_id,
            @RequestBody Language language) {
        if(language.getLanguage_name() != null || language.getLanguage_description() != null || language.getLanguage_standard_code() != null) {
            return languageService.update(language_id, language);
        } else {
            throw new BadRequestException("All fields are required");
        }
    }

    @GetMapping("/{language_id}")
    public Language getLanguageById(
            @PathVariable("language_id") UUID language_id
    ) {
        return languageService.getById(language_id);
    }
}
