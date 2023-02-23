package com.prwebsitebackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ScheduleDataDto {
    private Long id;
    @NotNull
    private Long scheduleId;
    @NotNull
    private LocalDateTime scheduleDate;
    @NotBlank
    private String location;
    @NotBlank
    private String staff;
    @NotNull
    private Integer hour;
    private LocalDateTime createdAt;
}
