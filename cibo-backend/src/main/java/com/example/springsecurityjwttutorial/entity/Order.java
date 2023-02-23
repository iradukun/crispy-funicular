package com.example.springsecurityjwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID orderId = UUID.randomUUID();

    @Column(nullable = false)
    private String names;
    @Column(nullable = true)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = true)
    private String address;
    @ManyToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToMany()
    private List<OrderProduct> products;
    private Long totalPrice;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;
    private String status = "pending";
    @ManyToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Restourant restourant;

}
