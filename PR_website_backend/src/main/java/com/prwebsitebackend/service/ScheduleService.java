package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.ScheduleDTO;
import com.prwebsitebackend.dto.ScheduleDataDto;
import com.prwebsitebackend.dto.SuccessRatesDTO;
import com.prwebsitebackend.model.*;
import com.prwebsitebackend.repository.CastCountRepository;
import com.prwebsitebackend.repository.ScheduleDataRepository;
import com.prwebsitebackend.repository.ScheduleRepository;
import com.prwebsitebackend.repository.SuccessRatesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final SuccessRatesRepository successRatesRepository;
    private final ScheduleDataRepository scheduleDataRepository;
    private final CastCountRepository castCountRepository;
    private  final TeamService teamService;

    public ScheduleService(ScheduleRepository scheduleRepository, SuccessRatesRepository successRatesRepository, ScheduleDataRepository scheduleDataRepository, CastCountRepository castCountRepository, TeamService teamService) {
        this.scheduleRepository = scheduleRepository;
        this.successRatesRepository = successRatesRepository;
        this.scheduleDataRepository = scheduleDataRepository;
        this.castCountRepository = castCountRepository;
        this.teamService = teamService;
    }

    /**TODO
     * Make hour A unit of time instead of a String
     **/
    public Schedule createSchedule(ScheduleDTO schedule) {
        Team team = teamService.getTeam(Long.parseLong(schedule.getTeamId()));
        if (team != null) {
            Schedule sched = new Schedule();
            sched.setHour(schedule.getHour());
            sched.setStaff(schedule.getStaff());
            sched.setAddress(schedule.getAddress());
            sched.setTeam(team);
            return scheduleRepository.save(sched);
        }
        return null;
    }

    public Schedule updateSchedule(String id,ScheduleDTO schedule) {
       Optional <Schedule> sched = scheduleRepository.findById(Long.parseLong(id));
        if(sched.isPresent()){
            if(schedule.getHour() != null && schedule.getStaff() != null){
                sched.get().getHour().addAll(schedule.getHour());
                sched.get().getStaff().addAll(schedule.getStaff());
            }
            sched.get().setAddress(schedule.getAddress());
            return scheduleRepository.save(sched.get());
        }
        return null;
    }
    //
    /**TODO
     *Implement get_schedules to return a list of schedule for a team based on dates
     *Get Only All Schedules of a team,
     **/
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
    /**TODO
     *implement get_schedules to return a list of schedule for a team based on dates
     **/
    public List<Schedule> getSchedule(LocalDate date) {
        List<Schedule> sched = scheduleRepository.findScheduleByDay(date);
        if(!sched.isEmpty()){
            return sched;
        }
        return null;
    }

    public SuccessRates createSuccessRate(String id, SuccessRatesDTO success) {
        ModelMapper mapper = new ModelMapper();
        Optional<Schedule> sched = scheduleRepository.findById(Long.parseLong(id));
        SuccessRates successRates = mapper.map(success,SuccessRates.class);
        if(sched.isPresent() && successRates != null){
            sched.get().setSuccessRates(successRates);
            //Set Parent reference(Schedule) in child entity(SuccesRates)
            successRates.setSchedule(sched.get());
            return scheduleRepository.save(sched.get()).getSuccessRates();
        }
        return null;
    }

    public SuccessRates getSuccessRate(String id) {
        Optional<Schedule> sched = scheduleRepository.findById(Long.parseLong(id));
        return sched.map(Schedule::getSuccessRates).orElse(null);
    }

    public CastCount setCastCount(String id, CastCount castCount) {
        Optional<Schedule> sched = scheduleRepository.findById(Long.parseLong(id));
        CastCount count = castCountRepository.findCastCountBySchedule(Long.parseLong(id));
        if (sched.isPresent() && count == null) {
            sched.get().setCastCount(castCount);
            sched.get().getCastCount().setSchedule(sched.get());
            return scheduleRepository.save(sched.get()).getCastCount();
        }
        return null;
    }

    public CastCount getCastCount(String id) {
        Optional<Schedule> sched = scheduleRepository.findById(Long.parseLong(id));
        return sched.map(Schedule::getCastCount).orElse(null);
    }

    public ScheduleData addScheduleData(ScheduleDataDto data){
        Optional<Schedule> sched = scheduleRepository.findById(data.getScheduleId());
        if(sched.isPresent()){
            ScheduleData scheduleData = new ScheduleData();
            scheduleData.setScheduleId(data.getScheduleId());
            scheduleData.setScheduleDate(data.getScheduleDate());
            scheduleData.setLocation(data.getLocation());
            scheduleData.setHour(data.getHour());
            scheduleData.setStaff(data.getStaff());
            return scheduleDataRepository.save(scheduleData);
        }
        return null;
    }

    public List<ScheduleData> getScheduleData(String scheduleId) {
        Schedule data = scheduleRepository.findById(Long.parseLong(scheduleId)).orElse(null);
        if(data != null){
            return scheduleDataRepository.findScheduleDataByScheduleId(Long.parseLong(scheduleId));
        }
        return null;
    }
}
