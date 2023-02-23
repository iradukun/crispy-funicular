package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import com.hcm.backend_service.v2.enums.EHospitalCategory;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ApiModel(value = "HospitalCategory", description = "The model for hospital category")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospital_category")
public class HospitalCategory extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID hospital_category_id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private EHospitalCategory hospitalCategory;

    @JsonIgnore
    @OneToMany(
            mappedBy = "hospitalCategory"
    )
    private List<Hospital> hospitals;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HospitalCategory that = (HospitalCategory) o;
        return hospital_category_id != null && Objects.equals(hospital_category_id, that.hospital_category_id);
    }

    public HospitalCategory(EHospitalCategory hospitalCategory) {
        this.hospitalCategory = hospitalCategory;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
