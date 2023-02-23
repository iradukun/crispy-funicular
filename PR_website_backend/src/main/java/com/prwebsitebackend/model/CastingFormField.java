package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prwebsitebackend.enums.FieldType;

import javax.persistence.*;

@Entity
public class CastingFormField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private FieldType type;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="form_id", nullable=false)
    private CastingForm castingForm;

    public CastingForm getCastingForm() {
        return castingForm;
    }

    public void setCastingForm(CastingForm castingForm) {
        this.castingForm = castingForm;
    }

    public CastingFormField(String name, FieldType type, CastingForm castingForm) {
        this.name = name;
        this.type = type;
        this.castingForm = castingForm;
    }

    public CastingFormField(String name, FieldType type) {
        this.name = name;
        this.type = type;
    }

    public CastingFormField() {
    }

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

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }
}
