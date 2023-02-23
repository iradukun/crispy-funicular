package com.prwebsitebackend.dto;

import com.prwebsitebackend.model.StatisticalFormColumn;

import java.time.LocalDateTime;
import java.util.List;

public class StatisticalFormDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<StatisticalFormColumnDTO> columns;

    public StatisticalFormDTO(Long id, String name, List<StatisticalFormColumnDTO> columns) {
        this.id = id;
        this.name = name;
        this.columns = columns;
    }

    public StatisticalFormDTO() {
    }
    //private TeamDto team;

//    public TeamDto getTeam() {
//        return team;
//    }
//
//    public void setTeam(TeamDto team) {
//        this.team = team;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StatisticalFormColumnDTO> getColumns() {
        return columns;
    }

    public void setColumns(List<StatisticalFormColumnDTO> columns) {
        this.columns = columns;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
