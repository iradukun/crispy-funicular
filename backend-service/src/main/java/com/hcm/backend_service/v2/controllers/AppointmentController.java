package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.BookAppointmentDto;
import com.hcm.backend_service.v2.dtos.ChangeScheduleDto;
import com.hcm.backend_service.v2.dtos.SendAppointmentDto;
import com.hcm.backend_service.v2.models.Appointment;
import com.hcm.backend_service.v2.models.AppointmentManager;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.payload.AppointmentPayload;
import com.hcm.backend_service.v2.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/appointments")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @PostMapping("/{schedule_id}")
    public Appointment createAppointment(@RequestBody @Valid AppointmentPayload appointmentPayload,
                                         @PathVariable UUID schedule_id,
                                         @AuthenticationPrincipal UserDetails user) {
        Timestamp start_time = Timestamp.valueOf(appointmentPayload.getStart_time());
        Timestamp end_time = Timestamp.valueOf(appointmentPayload.getEnd_time());
        return
                appointmentService.createAppointment(start_time, end_time, schedule_id, (User) user);
    }

    @PutMapping("/{appointmentId}")
    public Appointment bookAppointment(
            @PathVariable("appointmentId") UUID appointmentId,
            @RequestBody BookAppointmentDto bookAppointmentDto,
            @AuthenticationPrincipal UserDetails user
            ) {
        return appointmentService.bookAppointment(appointmentId, bookAppointmentDto, (User) user);
    }

    //map appointment to a doctor(here i change status of appointment to ACCEPTED and set the doctor to new doctor)
    @GetMapping("/hospital/{hospitalId}")
    public List<Appointment> getHospitalAppointments(@PathVariable("hospitalId") UUID hospitalId) {
        return appointmentService.getHospitalAppointments(hospitalId);
    }
//    @PatchMapping("/change-appointment-schedule")
//    public Appointment changeSchedule(@RequestBody ChangeScheduleDto dto, @AuthenticationPrincipal UserDetails user) {
//        return appointmentService.changeAppointmentSchedule(dto, (AppointmentManager) user);
//    }
    @GetMapping("/hospital/{hospitalId}/{serviceId}")
    public List<Appointment> getHospitalAppointmentsByService(
            @PathVariable("hospitalId") UUID hospitalId,
            @PathVariable("serviceId") UUID serviceId
    ) {
        return appointmentService.getHospitalAppointmentsByService(hospitalId, serviceId);
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<Appointment> getScheduleAppointments(@PathVariable("scheduleId") UUID scheduleId) {
        return appointmentService.getScheduleAppointments(scheduleId);
    }

    @GetMapping("/schedule/{scheduleId}/unbooked")
    public List<Appointment> getUnBookedAppointmentsInSchedule(
            @PathVariable("scheduleId") UUID scheduleId
    ){
        return appointmentService.getUnBookedAppointmentsInSchedule(scheduleId);
    }

    @GetMapping("/schedule/{scheduleId}/booked")
    public List<Appointment> getBookedAppointmentsInSchedule(@PathVariable("scheduleId") UUID scheduleId) {
        return appointmentService.getBookedAppointmentsInSchedule(scheduleId);
    }

    @GetMapping("/hospital/{hospitalId}/doctor/{doctorId}")
    public List<Appointment> getDoctorAppointments(@PathVariable("hospitalId") UUID hospitalId, @PathVariable("doctorId") UUID doctorId) {
        return appointmentService.getDoctorAppointments(hospitalId, doctorId);
    }
//    @PostMapping("/send-appointment")
//    public Appointment sendAppointment(
//            @AuthenticationPrincipal UserDetails doctor,
//            @RequestBody SendAppointmentDto sendAppointmentDto) {
//        Timestamp start_time = Timestamp.valueOf(sendAppointmentDto.getStart_time());
//        Timestamp end_time = Timestamp.valueOf(sendAppointmentDto.getEnd_time());
//        return appointmentService.sendAppointment(doctor, sendAppointmentDto, start_time, end_time);
//    }

    @PatchMapping("/appointment-manager/edit/{appointmentId}")
    public Appointment appointmentManagerEditAppointment(
            @RequestBody AppointmentPayload payload,
            @PathVariable("appointmentId") UUID appointmentId
    ){
        return appointmentService.appointmentManagerEditAppointment(appointmentId, payload);
    }
    @PatchMapping("/patient/edit/{appointmentId}")
    public Appointment patientEditAppointment(
            @RequestBody BookAppointmentDto appointmentDto,
            @PathVariable("appointmentId") UUID appointmentId
    ){
        return appointmentService.patientEditAppointment(appointmentId, appointmentDto);
    }
}

