package com.prwebsitebackend.service;

import com.prwebsitebackend.model.BackUpSettings;
import com.prwebsitebackend.model.User;
import com.prwebsitebackend.repository.BackUpSettingsRepository;
import com.prwebsitebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final BackUpSettingsRepository backUpSettingsRepository;

    @Autowired
    public MailService(JavaMailSender javaMailSender, UserRepository userRepository, BackUpSettingsRepository backUpSettingsRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
        this.backUpSettingsRepository = backUpSettingsRepository;
    }

    public void sendMail(String title, String mailMessage) {
        User admin = userRepository.findUserByRole("SUPER_ADMIN");
        BackUpSettings settings = backUpSettingsRepository.findByUserId(admin.getUserId());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ntagungiraali@gmail.com");
        message.setTo(settings.getAddress());
        message.setSubject(title);
        message.setText(mailMessage);
        javaMailSender.send(message);
    }
}
