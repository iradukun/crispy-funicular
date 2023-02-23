package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.CastingFormDataDto;
import com.prwebsitebackend.dto.CastingFormDto;
import com.prwebsitebackend.dto.SaveCastingDataDto;
import com.prwebsitebackend.model.CastingForm;
import com.prwebsitebackend.model.CastingFormData;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.CastingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/castings")
public class CastingController {
    private final CastingService castingService;
    public CastingController(CastingService castingService) {
        this.castingService = castingService;
    }

    @GetMapping
    public APIResponse getAllCastings() {
        ModelMapper mapper = new ModelMapper();
        TypeToken<List<CastingFormDto>> token = new TypeToken<>() {
        };
        List<CastingForm> casting = castingService.getAllCastingForms();
        if(casting.isEmpty()){
            return new APIResponse("failed", "No Casting Forms Found", new ArrayList<CastingFormDataDto>());
        }
        return new APIResponse("success","Fetch Casting Forms Successful",mapper.map(casting, token.getType()));
    }

    @PostMapping
    public APIResponse addNew(@Valid @RequestBody CastingFormDto casting) {
        ModelMapper mapper = new ModelMapper();
        CastingFormDto form = mapper.map(castingService.createCastingForm(casting), CastingFormDto.class);
        if(form == null)
            return new APIResponse("failed", "Casting Form Creation Failed", new CastingFormDto());
        else
            return new APIResponse("success", "Casting Form Created Successfully", form);
    }

    @PostMapping("/submit")
    public APIResponse saveData(@Valid @RequestBody SaveCastingDataDto casting) {
        CastingFormData data = castingService.SaveFormData(casting);
        if(data == null)
            return new APIResponse("failed", "Casting Form Data Submission Failed", new CastingFormData());
        else
            return new APIResponse("success","Successfully Saved Casting Data", data);
    }

    @GetMapping("/data/{formId}")
    public APIResponse getAllData(@PathVariable("formId") String formId) {

        List<CastingFormDataDto> castings = new ArrayList<>();
        Set<CastingFormData> set = castingService.getCastingData(formId);
        for(CastingFormData data : set){
            CastingFormDataDto d = new CastingFormDataDto();
            d.setId(data.getId());
            d.setFormId(data.getFormId().getId());
            d.setFieldValue(data.getFieldValue());
            castings.add(d);
        }
        return new APIResponse("success","Get Form Data successful",castings);
    }

    @GetMapping("/{id}")
    public APIResponse findCastingFormById(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        CastingForm casting = castingService.getCastingById(id);
        if (casting != null)
            return new APIResponse("success","Get form successful",mapper.map(casting, CastingFormDto.class));
        else
            return new APIResponse("failed","Failed to get form. Form may not exist",new CastingFormDto());
    }

    @DeleteMapping("delete/data/{id}")
    public APIResponse deleteData(@PathVariable("id") Long id) {
        if(castingService.deleteCastingDataById(id))
            return new APIResponse("success","Successfully deleted data",null);
        else
            return new APIResponse("failed","Failed to delete data",null);
    }
}
