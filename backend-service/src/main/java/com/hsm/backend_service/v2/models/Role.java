package com.hcm.backend_service.v2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcm.backend_service.v2.audits.InitiatorAudit;
import com.hcm.backend_service.v2.enums.ERole;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends InitiatorAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(hidden = true)
    private UUID role_id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required.")
    @Column(unique = true)
    private ERole role;

    @Column(name="description")
    private String description;
    @OneToMany(
            mappedBy = "role",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<User> users;

    public Role(ERole role, String description) {
        this.role = role;
        this.description = description;
    }
}
