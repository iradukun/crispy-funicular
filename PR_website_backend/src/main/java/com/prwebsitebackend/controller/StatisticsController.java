package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.StatisticalFormDTO;
import com.prwebsitebackend.model.StatisticalForm;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.StatisticsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping()
    public ResponseEntity<APIResponse> CreateStatisticalForm(@RequestBody StatisticalFormDTO statisticalFormDTO) {
        ModelMapper mapper = new ModelMapper();
        StatisticalForm statisticalForm = statisticsService.CreateStatisticalForm(statisticalFormDTO);
        if(statisticalForm!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse("created","Statistical form created successfully",mapper.map(statisticalForm, StatisticalFormDTO.class)));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("error","Statistical form creation failed",new StatisticalFormDTO()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateStatisticalForm(@PathVariable String id,@RequestBody StatisticalFormDTO statisticalFormDTO){
        ModelMapper mapper = new ModelMapper();
        StatisticalForm statisticalForm = statisticsService.updateStatisticalForm(id,statisticalFormDTO);
        if (statisticalForm != null){
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new APIResponse("success","Update statistical form successful",mapper.map(statisticalForm, StatisticalFormDTO.class))
                );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new APIResponse("error","StatisticalForm not found",new StatisticalFormDTO())
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getStatisticalForm(@PathVariable String id){
        ModelMapper mapper = new ModelMapper();
        StatisticalForm statisticalForm = statisticsService.getStatisticalForm(id);
        if(statisticalForm == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new APIResponse("error","StatisticalForm not found",new StatisticalFormDTO())
                    );
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new APIResponse("success","StatisticalForm Fetched Successfully",mapper.map(statisticalForm, StatisticalFormDTO.class))
                );
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse>getAllStatisticalForms(){
        ModelMapper mapper = new ModelMapper();
        TypeToken<List<StatisticalFormDTO>> token = new TypeToken<List<StatisticalFormDTO>>(){};
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new APIResponse("success","StatisticalForms Fetched Successfully",mapper.map(statisticsService.getAllStatisticalForms(), token.getType()))
                );
    }

}
