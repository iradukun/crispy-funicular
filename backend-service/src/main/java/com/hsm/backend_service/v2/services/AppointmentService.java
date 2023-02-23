package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.BookAppointmentDto;
import com.hcm.backend_service.v2.dtos.ChangeScheduleDto;
import com.hcm.backend_service.v2.dtos.SendAppointmentDto;
import com.hcm.backend_service.v2.enums.EMessageVia;
import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.models.*;
import com.hcm.backend_service.v2.payload.AppointmentPayload;
import com.hcm.backend_service.v2.repositories.AppointmentRepository;
import com.hcm.backend_service.v2.enums.EAppointmentStatus;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    AppointmentStatusService appointmentStatusService;

    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentManagerService appointmentManagerService;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    ServiceService serviceService;
    @Autowired
    MailService mailService;

    public Appointment createAppointment(Timestamp start_time, Timestamp end_time, UUID schedule_id, User patient) {
        Schedule schedule =
                scheduleService.getScheduleById(schedule_id);
        if(schedule == null) {
            throw new ElementNotFoundException("schedule not found");
        }
        AppointmentStatus appointmentStatus =
                appointmentStatusService.getStatusByName(EAppointmentStatus.CREATED);
        Appointment newAppointment =
                new Appointment(schedule, appointmentStatus, start_time, end_time);
        return appointmentRepository.save(newAppointment);
    }

    public Appointment findAppointmentById(UUID appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if(!appointmentOptional.isPresent()) {
            throw new ElementNotFoundException("appointment not found");
        }
        return appointmentOptional.get();
    }
    //map appointment to a doctor(here I change status of appointment to ACCEPTED and set the doctor to new doctor)

    @Transactional
    public Appointment changeAppointmentStatus(EAppointmentStatus newStatus, UUID appointmentId) {
        Optional<Appointment> appointmentOptional =
                appointmentRepository.findById(appointmentId);
        if(!appointmentOptional.isPresent()) {
            throw new ElementNotFoundException("appointment not found");
        }
        Appointment appointment =
                appointmentOptional.get();
        AppointmentStatus status =
                appointmentStatusService.getStatusByName(newStatus);
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

//    @Transactional
//    public List<Appointment> approveAll(User appointmentManager) {
//        AppointmentStatus appointmentStatus =
//                appointmentStatusService.getStatusByName(EAppointmentStatus.ACCEPTED);
//        AppointmentManager manager = appointmentManagerService.getAppointmentManagerById(appointmentManager.getId());
//        Hospital hospital =
//                manager.getHospital();
//        if(hospital == null) {
//            throw new BadRequestException("invalid hospital");
//        }
//        List<Schedule> scheduleList = hospital.getSchedules();
//        List<Appointment> appointments = new ArrayList<>();
//        for (Schedule schedule:scheduleList) {
//            appointments.addAll(schedule.getAppointments());
//        }
//        for (Appointment appointment:appointments) {
//            appointment.setStatus(appointmentStatus);
//        }
//        appointmentRepository.saveAll(appointments);
//        return appointments;
//    }

    public List<Appointment> getHospitalAppointments(UUID hospitalId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        if(hospital == null) {
            throw new  ElementNotFoundException("hospital not found");
        }
        List<Schedule> schedules = hospital.getSchedules();
        List<Appointment> appointments = new ArrayList<>();
        for (Schedule schedule:schedules) {
            appointments.addAll(schedule.getAppointments());
        }
        return appointments;
    }

//    @Transactional
//    public Appointment changeAppointmentSchedule(ChangeScheduleDto dto, AppointmentManager user) {
//        Appointment appointment =
//                findAppointmentById(dto.getAppointmentId());
//        if(!appointment.getSchedule().getHospital().getAppointmentManagers().contains(user)) {
//            throw new BadRequestException("not allowed to update this appointment");
//        }
//        Schedule newSchedule =
//                scheduleService.getScheduleById(dto.getNewScheduleId());
//        appointment.setSchedule(newSchedule);
//        return appointmentRepository.save(appointment);
//    }

    public List<Appointment> getHospitalAppointmentsByService(UUID hospitalId, UUID serviceId) {
        Hospital hospital =
                hospitalService.getHospitalById(hospitalId);
        com.hcm.backend_service.v2.models.Service service =
                serviceService.getServiceById(serviceId);

        List<Schedule> schedules =
                scheduleService.getHospitalSchedulesByService(hospital, service);
        List<Appointment> appointments = new ArrayList<>();
        for (Schedule schedule:schedules) {
            appointments.addAll(schedule.getAppointments());
        }
        return appointments;
    }

    public List<Appointment> getScheduleAppointments(UUID scheduleId) {
        Schedule schedule =
                scheduleService.getScheduleById(scheduleId);
        return schedule.getAppointments();
    }

    public List<Appointment> getDoctorAppointments(UUID hospitalId, UUID doctorId) {
        List<Schedule> schedules =
                scheduleService.getDoctorSchedules(hospitalId, doctorId);
        Collections.sort(schedules, new Comparator<Schedule>(){
            public int compare(Schedule o1, Schedule o2){
                if(o1.getStart_time().compareTo(o2.getEnd_date()) == 0) {
                    return 0;
                } else if (o1.getStart_date().compareTo(o2.getEnd_date()) > 0) {
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        List<Appointment> appointments =
                new ArrayList<Appointment>();
        for (Schedule schedule:schedules) {
            appointments.addAll(schedule.getAppointments());
        }
        return appointments;
    }

//    public Appointment sendAppointment(UserDetails doctor, SendAppointmentDto sendAppointmentDto, Timestamp start_time, Timestamp end_time) {
//        if(doctor instanceof Doctor) {
//            User patient =
//                    userService.getUserByEmailOrMobile(sendAppointmentDto.getEmailOrPhoneVia(), sendAppointmentDto.getEmailOrPhoneVia());
//            //
//            if(sendAppointmentDto.getVia() == EMessageVia.EMAIL) {
//                mailService.sendAppointmentNotificationEmail(patient.getEmail(), patient.getFullName(), ((Doctor) doctor).getFullName(), sendAppointmentDto.getService(), start_time, end_time);
//            }
//            //else send sms
//
//            Appointment appointment =
//                    new Appointment(start_time, end_time, sendAppointmentDto.getVia(), sendAppointmentDto.getEmailOrPhoneVia());
//            return createAppointment(appointment, sendAppointmentDto.getScheduleId(), patient);
//        }else{
//            throw new BadRequestException("user is not a doctor");
//        }
//    }


    public Appointment bookAppointment(UUID appointmentId, BookAppointmentDto bookAppointmentDto, User user) {
        Appointment appointment =
                findAppointmentById(appointmentId);
        AppointmentStatus bookedStatus =
                appointmentStatusService.getStatusByName(EAppointmentStatus.BOOKED);
        if(appointment.getStatus() == bookedStatus) {
            throw new BadRequestException("appointment already booked");
        }
        appointment.setPatient(user);
        appointment.setVia(bookAppointmentDto.getVia());
        appointment.setPhoneOrEmailVia(bookAppointmentDto.getPhoneOrEmailVia());
        return appointmentRepository.save(appointment);
    }

    public Appointment appointmentManagerEditAppointment(UUID appointmentId, AppointmentPayload payload) {
        Appointment appointment =
                findAppointmentById(appointmentId);
        AppointmentStatus status =
                appointmentStatusService.getStatusByName(EAppointmentStatus.BOOKED);
        //check if appointment is already booked
        if(appointment.getStatus() == status) {
            throw new BadRequestException("appointment already booked");
        }
        appointment.setStart_time(Timestamp.valueOf(payload.getStart_time()));
        appointment.setEnd_time(Timestamp.valueOf(payload.getEnd_time()));
        return appointmentRepository.save(appointment);
    }

    public Appointment patientEditAppointment(UUID appointmentId, BookAppointmentDto appointmentDto) {
        Appointment appointment =
                findAppointmentById(appointmentId);
        appointment.setVia(appointmentDto.getVia());
        appointment.setPhoneOrEmailVia(appointmentDto.getPhoneOrEmailVia());
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getUnBookedAppointmentsInSchedule(UUID scheduleId) {
        Schedule schedule =
                scheduleService.getScheduleById(scheduleId);
        AppointmentStatus unbookedStatus =
                appointmentStatusService.getStatusByName(EAppointmentStatus.CREATED);
        return getAppointments(schedule, unbookedStatus);
    }

    private List<Appointment> getAppointments(Schedule schedule, AppointmentStatus status) {
        List<Appointment> appointments = schedule.getAppointments();
        List<Appointment> wantedAppointments = new ArrayList<>();
        for (Appointment appointment:appointments) {
            if(appointment.getStatus() == status) {
                wantedAppointments.add(appointment);
            }
        }
        return wantedAppointments;
    }

    public List<Appointment> getBookedAppointmentsInSchedule(UUID scheduleId) {
        Schedule schedule =
                scheduleService.getScheduleById(scheduleId);
        AppointmentStatus bookedStatus =
                appointmentStatusService.getStatusByName(EAppointmentStatus.BOOKED);
        return getAppointments(schedule, bookedStatus);
    }
}
