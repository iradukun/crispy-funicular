package com.example.springsecurityjwttutorial.repository;

import com.example.springsecurityjwttutorial.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepo extends JpaRepository<OrderProduct, Long> {
}
