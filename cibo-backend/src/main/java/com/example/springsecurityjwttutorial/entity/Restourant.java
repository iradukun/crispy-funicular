package com.example.springsecurityjwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restourant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String restourantName;
    private String image;
    private String district;
    private String sector;
    private String cell;

    @OneToMany(
            mappedBy = "restourant",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Product> products;

    @OneToMany(
            mappedBy = "restourant",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<User> employees;

    @OneToMany(
            mappedBy = "restourant",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Order> orders;
    private Long orderNumber;
}
