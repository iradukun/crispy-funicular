package com.example.springsecurityjwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> order;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;
    private Long quantity;
}
