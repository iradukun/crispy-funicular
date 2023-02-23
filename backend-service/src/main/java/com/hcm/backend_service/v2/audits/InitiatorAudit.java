package com.hcm.backend_service.v2.audits;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdBy", "updatedBy"}, allowGetters = true)
public abstract class InitiatorAudit extends TimestampAudit{
    private static final Long serialVersionUID = 1L;

    @JsonIgnore
    @CreatedBy
    @Column(name = "created_by")
    @ApiModelProperty(hidden = true)
    private UUID createdBy;


    @JsonIgnore
    @LastModifiedBy
    @Column(name = "updated_by")
    @ApiModelProperty(hidden = true)
    private UUID updatedBy;
}
