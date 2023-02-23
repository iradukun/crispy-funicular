package com.example.springsecurityjwttutorial.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int id;
    private String address;
    private LocalDate date;
    private String email;
    private String names;
    private UUID order_id;
    private String phone;
    private String status;
    private LocalTime time;
    private Long total_price;
    private Long restourant_id;
    private Long user_id;
}
