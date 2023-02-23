package com.hcm.backend_service.v2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordMail(String toEmail, String names, String activationCodes) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@hcm.org");
        message.setTo(toEmail);
        message.setText("Dear " + names + "!\n" +
                "\n" +
                "You've requested to reset password to your hcm account, " +
                "your verification code is " + activationCodes + ". \n" +
                "\n" +
                "This code expires in 5 minutes.\n" +
                "\n" +
                "If you have any questions, send us an email support@hcm.org.\n" +
                "\n" +
                "We’re glad you’re here!\n" +
                "\n");
        message.setSubject("HCM APPS VERIFICATION CODE");
        mailSender.send(message);
    }
    public void sendAppointmentNotificationEmail(String toEmail, String names, String doctorNames, String service, Timestamp start_time, Timestamp end_time) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@hcm.org");
        message.setTo(toEmail);
        message.setText("Dear " + names + "!\n" +
                "\n" +
                "Doctor named " + doctorNames + "" +
                "Have requested an appointment with you at "+"" +
                start_time + " until " + end_time);
    }
}