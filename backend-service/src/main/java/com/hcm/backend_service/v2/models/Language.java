package com.hcm.backend_service.v2.models;

import com.hcm.backend_service.v2.audits.InitiatorAudit;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@ApiModel(value = "Language", description = "The model for language")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "language")
public class Language extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID language_id;

    @NotNull(message = "Language name is required.")
    @Column(unique = true)
    private String language_name;

    @NotNull(message = "Language standard code is required.")
    @Column(unique = true)
    private String language_standard_code;

    @NotNull(message = "Language description is required.")
    @Column(unique = true)
    private String language_description;

    public Language(String language_name, String language_standard_code, String language_description) {
        this.language_name = language_name;
        this.language_standard_code = language_standard_code;
        this.language_description = language_description;
    }
}
