package com.example.springsecurityjwttutorial.repository;

import com.example.springsecurityjwttutorial.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, Long> {
    public List<Order> findByUserId(Long userId);
    public List<Order> findByRestourantId(Long restourantId);

    public Order findByOrderId(UUID orderId);

    @Query(value = "SELECT * FROM orders WHERE user_id = ?", nativeQuery = true)
    public List<Order> getUserOrders(Long userId);
}
