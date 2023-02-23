package com.prwebsitebackend.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SuccessfullMessageDto {
    private static String message = "Request was successfull!";

    public String getMessage() {
        return message;
    }
}
