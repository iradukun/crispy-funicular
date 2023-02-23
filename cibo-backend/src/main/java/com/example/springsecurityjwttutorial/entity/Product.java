package com.example.springsecurityjwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String price;
    private String image;

    @ManyToOne
    private Restourant restourant;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderProduct> orderProducts;
}
