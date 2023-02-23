package com.example.springsecurityjwttutorial.models;

import com.example.springsecurityjwttutorial.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequest {
    private Order order;
}
