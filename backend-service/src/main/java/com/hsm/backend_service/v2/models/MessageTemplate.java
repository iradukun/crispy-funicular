package com.hcm.backend_service.v2.models;

import com.hcm.backend_service.v2.enums.EMessageTemplateType;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@ApiModel(value = "MessageTemplate", description = "The model for message template")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message_template")
public class MessageTemplate {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(name = "messaget_template_id", columnDefinition = "VARCHAR(255)")
    private UUID messageTemplateId;

    private EMessageTemplateType type;

    private String title;

    @Lob
    private String messageBody;
    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "message_template_hospital",
            joinColumns = @JoinColumn(name = "message_template_id"), inverseJoinColumns = @JoinColumn(name = "hospital_id")
    )
    private Hospital hospital;

    public MessageTemplate(EMessageTemplateType type, String title, String messageBody) {
        this.type = type;
        this.title = title;
        this.messageBody = messageBody;
    }
}
