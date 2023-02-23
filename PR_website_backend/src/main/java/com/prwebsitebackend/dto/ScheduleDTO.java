package com.prwebsitebackend.dto;

import com.prwebsitebackend.model.CastCount;
import com.prwebsitebackend.model.SuccessRates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleDTO {
    private Long id;
    private String teamId;
    private Set<Integer> hour;
    private Set<String> staff;
    private LocalDate day;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SuccessRatesDTO successRates;
    private CastCountDto castCount;
}
