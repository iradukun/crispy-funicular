package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CastingForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @JsonManagedReference
    @OneToMany(
            mappedBy = "castingForm",
            fetch = FetchType.LAZY
    )
    private List<CastingFormField> fields;

    public CastingForm(String name, List<CastingFormField> fields) {
        this.name = name;
        this.fields = fields;
    }

    public CastingForm(String name) {
        this.name=name;
    }
    public CastingForm() {
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
    public List<CastingFormField> getFields() {
        return fields;
    }
    public void setFields(List<CastingFormField> fields) {
        this.fields = fields;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
