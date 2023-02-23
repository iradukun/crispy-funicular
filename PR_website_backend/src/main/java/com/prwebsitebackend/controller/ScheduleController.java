package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.CastCountDto;
import com.prwebsitebackend.dto.ScheduleDTO;
import com.prwebsitebackend.dto.ScheduleDataDto;
import com.prwebsitebackend.dto.SuccessRatesDTO;
import com.prwebsitebackend.model.CastCount;
import com.prwebsitebackend.model.Schedule;
import com.prwebsitebackend.model.ScheduleData;
import com.prwebsitebackend.model.SuccessRates;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/new")
    public APIResponse newSchedule(@RequestBody ScheduleDTO schedule) {
        ModelMapper mapper = new ModelMapper();
        Schedule sched = scheduleService.createSchedule(schedule);
        if(sched != null){
            ScheduleDTO schedDTO = mapper.map(sched, ScheduleDTO.class);
            return new APIResponse("success","Create schedule successful",schedDTO);
        }
        return new APIResponse("failed", "Failed to create schedule", new ScheduleDTO());
    }

    @PutMapping("/update/{id}")
    public APIResponse updateSchedule(@PathVariable String id,@RequestBody ScheduleDTO schedule){
        ModelMapper mapper = new ModelMapper();
        Schedule sched = scheduleService.updateSchedule(id,schedule);
        if(sched != null){
            ScheduleDTO schedDTO = mapper.map(sched, ScheduleDTO.class);
            return new APIResponse("success","Update schedule successful",schedDTO);
        }
        return new APIResponse("failed", "Failed to update schedule. The schedule you are updating does not exist", new ScheduleDTO());
    }
    @PostMapping("/success_rate/{id}")
    public APIResponse creatSuccessRates(@PathVariable String id, @RequestBody SuccessRatesDTO success){
        ModelMapper mapper = new ModelMapper();
        SuccessRates sched = scheduleService.createSuccessRate(id,success);
        if(sched != null){
            SuccessRatesDTO successRatesDTO = mapper.map(sched,SuccessRatesDTO.class);
            return new APIResponse("success","Create success rates successful",successRatesDTO);
        }
        return new APIResponse("failed","Failed to create success rates",new SuccessRatesDTO());
    }
    @GetMapping("/success/{id}")
    public APIResponse getSuccessRates(@PathVariable String id){
        ModelMapper mapper = new ModelMapper();
        SuccessRates sched = scheduleService.getSuccessRate(id);
        if(sched != null){
            SuccessRatesDTO successRatesDTO = mapper.map(sched,SuccessRatesDTO.class);
            return new APIResponse("success","Fetch success rates successful",successRatesDTO);
        }
        return new APIResponse("failed","Failed to fetch success rates", new SuccessRatesDTO());
    }
    @GetMapping("/all")
    public APIResponse getSchedules(){
        ModelMapper mapper = new ModelMapper();
        TypeToken<List<ScheduleDTO>> token = new TypeToken<>() {};
        List<Schedule>schedules=scheduleService.getAllSchedules();
        if(!schedules.isEmpty()){
            List<ScheduleDTO> scheduleDTOS = mapper.map(schedules, token.getType());
            return new APIResponse("success","Fetch success rates successful",scheduleDTOS);
        }
        return new APIResponse("failed","No schedules found", null);
    }
        /**TODO
         * Get Only Schedules of a team
         **/
    @GetMapping("find/{date}")
    public APIResponse getSchedulesByDate(@PathVariable String date){
        ModelMapper mapper = new ModelMapper();
        LocalDate dateTime = LocalDate.parse(date);
        TypeToken<List<ScheduleDTO>> token = new TypeToken<>() {};
        List<Schedule>schedules=scheduleService.getSchedule(dateTime);
        if(schedules != null){
            List<ScheduleDTO> scheduleDTOS = mapper.map(schedules, token.getType());
            return new APIResponse("success","Fetched All Schedules",scheduleDTOS);
        }
        return new APIResponse("failed","No Schedules available fo fetch",new ScheduleDTO());
    }

    @PutMapping("/cast_count/{scheduleId}")
    public APIResponse createCastCount(@PathVariable String scheduleId, @RequestBody CastCountDto castCount){
        ModelMapper mapper = new ModelMapper();
        CastCount count = scheduleService.setCastCount(scheduleId,mapper.map(castCount, CastCount.class));
        System.out.println(count);
        if(count != null){
          return new APIResponse("success","Added cast count successfully",mapper.map(count,CastCountDto.class));
        }
        return new APIResponse("failed","Unable to add cast count", new CastCountDto());
    }

    @GetMapping("/cast_count/{scheduleId}")
    public APIResponse getRecruitments(@PathVariable String scheduleId){
        ModelMapper mapper = new ModelMapper();
        CastCount count = scheduleService.getCastCount(scheduleId);
        if(count != null){
            return new APIResponse("success","Fetch recruitments successful",mapper.map(count,CastCountDto.class));
        }
        return new APIResponse("failed","Unable to fetch recruitments", new CastCountDto());
    }

    @PostMapping("/data")
    public APIResponse getScheduleData(@Valid @RequestBody ScheduleDataDto schedule){
        ModelMapper mapper = new ModelMapper();
        ScheduleData sched = scheduleService.addScheduleData(schedule);
        if(sched != null){
            return new APIResponse("success","Fetch schedule data successful",mapper.map(sched,ScheduleDataDto.class));
        }
        return new APIResponse("failed","Unable to fetch schedule data", new ScheduleDataDto());
    }

    @GetMapping("/data/{ScheduleId}")
    public APIResponse getScheduleData(@PathVariable String ScheduleId){
        TypeToken<List<ScheduleDataDto>> token = new TypeToken<>() {};
        ModelMapper mapper = new ModelMapper();
        List<ScheduleData> sched = scheduleService.getScheduleData(ScheduleId);
        if(sched != null){
            return new APIResponse("success","Fetch schedule data successful",mapper.map(sched, token.getType()));
        }
        return new APIResponse("failed","Unable to fetch schedule data. You may be using a non-existent Schedule", token.getType());
    }

}
