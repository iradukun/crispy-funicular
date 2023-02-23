package com.prwebsitebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastCountDto {
    private Long id;
    private Long castCount;
    private Date startDate;
    private Date endDate;
}
