package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.exceptions.IncorrectElementException;
import com.hcm.backend_service.v2.models.*;
import com.hcm.backend_service.v2.payload.SchedulePayload;
import com.hcm.backend_service.v2.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    ServiceService serviceService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    ScheduleManagerService scheduleManagerService;
    @Autowired
    ScheduleStatusService scheduleStatusService;
    @Qualifier("bCryptPasswordEncoder")
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    HospitalService hospitalService;
    public Schedule createSchedule(Schedule schedule, User scheduleManager, SchedulePayload schedulePayload) {

//        ScheduleStatus scheduleStatus =
//                scheduleStatusService.getStatusByName(ScheduleStatusEnum.ACTIVE);
//        schedule.setStatus(scheduleStatus);
        return saveSchedule(schedule, scheduleManager, schedulePayload);

    }

    @Transactional
    public Schedule saveSchedule(Schedule schedule, User scheduleManager, SchedulePayload schedulePayload) {
        User scheduler = scheduleManagerService.getSchedulerById(scheduleManager.getId());
        schedule.setScheduler(scheduler);
        User doctor = doctorService.getDoctorById(schedulePayload.getDoctor_id());
        schedule.setDoctor(doctor);
        com.hcm.backend_service.v2.models.Service service = serviceService.getServiceById(schedulePayload.getService_id());
        schedule.setService(service);
        return scheduleRepository.save(schedule);
    }

    public Schedule getScheduleById(UUID schedule_id) {
        Schedule schedule =
                scheduleRepository.findById(schedule_id).get();
        if(schedule == null) {
            throw new Error("schedule not found");
        }
        return schedule;
    }
    public List<Schedule> getDoctorSchedules(UUID hospitalId, UUID doctorId) {
        User doctor = doctorService.getDoctorById(doctorId);
        if(doctor == null) {
            throw new ElementNotFoundException("doctor not found");
        }
        if(doctor instanceof Doctor) {
            Hospital hospital =
                    hospitalService.getHospitalById(hospitalId);
            return scheduleRepository.findAllByHospitalAndDoctor(hospital, (Doctor) doctor);
        }else{
            throw new BadRequestException("user is not a doctor");
        }
    }

    public Schedule updateSchedule(UUID scheduleId, Schedule newSchedule) {
        Optional<Schedule> scheduleOptional =
                scheduleRepository.findById(scheduleId);
        if (!scheduleOptional.isPresent()) {
            throw new ElementNotFoundException("schedule not found");
        }
        Schedule updated = scheduleOptional.get();
        newSchedule.setSchedule_id(updated.getSchedule_id());
        return scheduleRepository.save(newSchedule);
    }


    public String deleteSchedule(UUID scheduleId, User user, String password) {
        if(!encoder.matches(password, user.getPassword())) {
            throw new IncorrectElementException("incorrect password!");
        }
        Optional<Schedule> scheduleOptional =
                scheduleRepository.findById(scheduleId);
        if(!scheduleOptional.isPresent()) {
            throw new ElementNotFoundException("schedule not found");
        }
        scheduleRepository.deleteById(scheduleId);
        return "schedule deleted successfully";
    }

    public List<Schedule> getHospitalSchedules(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        if(hospital == null) {
            throw new  ElementNotFoundException("hospital not found");
        }
        List<Schedule> schedules = hospital.getSchedules();
        return schedules;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getHospitalSchedulesByService(Hospital hospital, com.hcm.backend_service.v2.models.Service service) {
        List<Schedule> schedules =
                scheduleRepository.findAllByHospitalAndService(hospital, service);
        return schedules;
    }


}
