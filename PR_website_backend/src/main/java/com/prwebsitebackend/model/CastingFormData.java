package com.prwebsitebackend.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "casting_form_data")
public class CastingFormData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="form_id")
    private CastingForm formId;
    @ElementCollection
    @MapKeyColumn(name="field_id")
    @Column(name = "value_id")
    private Map<Long,String> fieldValue;

    public CastingFormData(CastingForm formId, Map<Long, String> data) {
        this.formId = formId;
        this.fieldValue = data;
    }
    public CastingFormData() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CastingForm getFormId() {
        return formId;
    }

    public void setFormId(CastingForm formId) {
        this.formId = formId;
    }

    public Map<Long, String> getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Map<Long, String> fieldValue) {
        this.fieldValue = fieldValue;
    }
}
