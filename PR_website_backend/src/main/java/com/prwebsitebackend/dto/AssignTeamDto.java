package com.prwebsitebackend.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class AssignTeamDto {
    Set<Long> teamIds;

    public Set<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(Set<Long> teamIds) {
        this.teamIds = teamIds;
    }
}
