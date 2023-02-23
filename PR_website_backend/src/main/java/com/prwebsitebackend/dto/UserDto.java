package com.prwebsitebackend.dto;

import com.prwebsitebackend.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long userId;
    private String id;
    private String username;
    private String role;
    private Set<TeamDto> teams;
    private List<BulletinDTO> bulletins;

    public UserDto(Long userId, String id, String username, String role) {
        this.userId = userId;
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
