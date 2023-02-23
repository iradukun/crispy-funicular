package com.prwebsitebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Long publicRelations;
    @NotNull
    private Long publicRelationsForm;
    @NotNull
    private Long accountActivityForm;
    @NotNull
    private Long teamLeader;
}