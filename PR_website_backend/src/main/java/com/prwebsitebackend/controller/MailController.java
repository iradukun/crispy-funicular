package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.MailDto;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public APIResponse sendNew(@RequestBody MailDto newEmail) {
        mailService.sendMail(newEmail.getSubject(), newEmail.getText());
        return new APIResponse("success", "Email Sent Successfully", true);
    }
}
