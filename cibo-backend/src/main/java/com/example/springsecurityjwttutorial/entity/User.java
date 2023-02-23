package com.example.springsecurityjwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity // Marks this class as an Entity
@Getter // Defines the getter methods of the member fields
@Setter // Defines the setter methods of the member fields
@ToString // Defines a meaningful toString implementation of this class
@NoArgsConstructor // Defines the default constructor
public class User {

    @Id // Marks the "id" field as the identifier of this entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // The "id" field is to be generated automatically
    private Long id;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role = "ROLE_BOOKER";

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Restourant restourant;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

}
