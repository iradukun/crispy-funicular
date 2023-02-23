package com.example.springsecurityjwttutorial.controllers;

import com.example.springsecurityjwttutorial.entity.Order;
import com.example.springsecurityjwttutorial.entity.Product;
import com.example.springsecurityjwttutorial.entity.Restourant;
import com.example.springsecurityjwttutorial.entity.User;
import com.example.springsecurityjwttutorial.exceptions.ResourceNotFound;
import com.example.springsecurityjwttutorial.models.OrderRequest;
import com.example.springsecurityjwttutorial.models.OrderResponse;
import com.example.springsecurityjwttutorial.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {
    @Autowired private OrderRepo orderRepo;
    @Autowired private OrderProductRepo orderProductRepo;

    ModelMapper mapper;
    public OrderController() {
        this.mapper = new ModelMapper();
    }


    @GetMapping("{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        //get orders by userId

        List<Order> userOrders = this.orderRepo.getUserOrders(userId);
        if(userOrders.size() == 0) {
            throw new ResourceNotFound("no orders found");
        }
        return userOrders;
    }


    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        System.out.println(request.getOrder());
        for (int i = 0; i < request.getOrder().getProducts().size(); i++) {
            orderProductRepo.save(request.getOrder().getProducts().get(i));
        }
        Order saved = this.orderRepo.save(request.getOrder());
        return saved;
    }

    @PutMapping("{orderId}")
    public String completePayment(@PathVariable UUID orderId) {
        Order order = this.orderRepo.findByOrderId(orderId);

        if(order == null) {
            throw new ResourceNotFound("order not found");
        }
        order.setStatus("paid");
        orderRepo.save(order);
//        System.out.println(order);
        return "updated successfully";
    }
}
