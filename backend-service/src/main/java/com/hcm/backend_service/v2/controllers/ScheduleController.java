package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.models.Schedule;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.payload.SchedulePayload;
import com.hcm.backend_service.v2.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/schedules")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @PostMapping
    public Schedule createSchedule(@RequestBody SchedulePayload schedulePayload, @AuthenticationPrincipal UserDetails user) {
        Schedule schedule = new Schedule(schedulePayload.getStart_date(), schedulePayload.getEnd_date(),schedulePayload.getStart_time(), schedulePayload.getEnd_time());
        return scheduleService.createSchedule(schedule, (User) user,schedulePayload);
    }
    //get all schedules
    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }
    //get schedules of a doctor in a given hospital
    @GetMapping("/doctor/{doctorId}/hospital/{hospitalId}")
    public List<Schedule> getDoctorSchedules(@PathVariable("doctorId") UUID doctorId, @PathVariable("hospitalId") UUID hospitalId) {
        return scheduleService.getDoctorSchedules(hospitalId, doctorId);
    }

    //update a schedule
    @PutMapping("/{scheduleId}")
    public Schedule updateSchedule(@PathVariable UUID scheduleId, @RequestBody Schedule newSchedule) {
        return scheduleService.updateSchedule(scheduleId, newSchedule);
    }

    //delete a schedule
    @DeleteMapping("/{scheduleId}/{userPassword}")
    public String deleteSchedule(@PathVariable("scheduleId") UUID scheduleId,
                                 @AuthenticationPrincipal UserDetails user,
                                 @PathVariable("userPassword") String userPassword
    ) {
        return scheduleService.deleteSchedule(scheduleId, (User) user, userPassword);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<Schedule> getHospitalSchedules(@PathVariable("hospitalId") UUID hospitalId) {
        return scheduleService.getHospitalSchedules(hospitalId);
    }

}
