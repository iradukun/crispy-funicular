package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class TimeDifference implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Timestamp start_time;

    @NotNull
    private Timestamp end_time;
}
