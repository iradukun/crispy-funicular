package com.prwebsitebackend.payload;

import com.prwebsitebackend.dto.UserDto;
import com.prwebsitebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthPayload {
    private String token;
    private UserDto user;
}
