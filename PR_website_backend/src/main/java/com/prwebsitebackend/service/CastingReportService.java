package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.CastingReportDto;
import com.prwebsitebackend.model.CastingReport;
import com.prwebsitebackend.repository.CastingReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CastingReportService {
    @Autowired
    CastingReportRepository castingReportRepository;
    public List<CastingReport> getAllCastingReport() {
        return castingReportRepository.findAll();
    }

    public CastingReport createCastingReportItem(CastingReportDto dto) {
        CastingReport cl = new CastingReport(dto.getEmployeeTeam(), dto.getCaster(), dto.getCountOfCasting(), dto.getCountOfPR(), dto.getPercentage(), dto.getBriefingPersonnel(), dto.getAverageOfPerson());
        CastingReport saved = castingReportRepository.save(cl);
        return saved;
    }

    public CastingReport createCastingReportItem(CastingReport dto) {
        CastingReport cl = new CastingReport(dto.getEmployeeTeam(), dto.getCaster(), dto.getCountOfCasting(), dto.getCountOfPR(), dto.getPercentage(), dto.getBriefingPersonnel(), dto.getAverageOfPerson());
        CastingReport saved = castingReportRepository.save(cl);
        return saved;
    }

    public CastingReport getCastingItemById(Long id) {
        return castingReportRepository.findById(id).orElse(null);
    }
}
