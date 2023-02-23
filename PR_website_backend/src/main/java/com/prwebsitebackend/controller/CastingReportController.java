package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.CastingReportDto;
import com.prwebsitebackend.model.CastingReport;
import com.prwebsitebackend.service.CastingReportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CastingReportController {
    private final CastingReportService castingReportService;

    public CastingReportController(CastingReportService castingReportService) {
        this.castingReportService = castingReportService;
    }

    @GetMapping
    public List getAllCastings() {
        return castingReportService.getAllCastingReport();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addNew(@RequestBody CastingReportDto castingReport) {
        CastingReportDto newCasting = castingReport;
        castingReportService.createCastingReportItem(newCasting);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CastingReport findCastingById(@PathVariable("id") Long id) {
        CastingReport castingReport = castingReportService.getCastingItemById(id);
        if (castingReport != null) {
            return castingReport;
        } else {
            return null;
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCasting(@RequestBody CastingReportDto new_info, @PathVariable("id") Long id) {
        CastingReport castingReport = castingReportService.getCastingItemById(id);
        castingReport.setEmployeeTeam(new_info.getEmployeeTeam());
        castingReport.setCountOfCasting(new_info.getCountOfCasting());
        castingReport.setCaster(new_info.getCaster());
        castingReport.setPercentage(new_info.getPercentage());
        castingReport.setAverageOfPerson(new_info.getAverageOfPerson());
        castingReport.setCountOfPR(new_info.getCountOfPR());
        castingReportService.createCastingReportItem(castingReport);
    }
}
