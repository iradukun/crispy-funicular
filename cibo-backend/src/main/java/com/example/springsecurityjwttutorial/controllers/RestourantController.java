package com.example.springsecurityjwttutorial.controllers;

import com.example.springsecurityjwttutorial.entity.Order;
import com.example.springsecurityjwttutorial.entity.Restourant;
import com.example.springsecurityjwttutorial.exceptions.ResourceNotFound;
import com.example.springsecurityjwttutorial.repository.OrderRepo;
import com.example.springsecurityjwttutorial.repository.RestourantRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restourants")
public class RestourantController {
    @Autowired private RestourantRepo restourantRepo;
    @Autowired private OrderRepo orderRepo;
    @GetMapping
    public List<Restourant> getRestourants(@RequestParam(defaultValue = "1") Integer page) {
        List<Restourant> restourants =  this.restourantRepo.findAll(PageRequest.of(page - 1, 18)).getContent();
        if(restourants.size() == 0) {
            System.out.println("Helloworld");
            throw new ResourceNotFound("no restourants found");
        }
        return restourants;
    }

    @GetMapping("/popular")
    public List<Restourant> getPopular() {
        return this.restourantRepo.findAll(PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "orderNumber"))).getContent();
    }

    @GetMapping("/orders/{restourantId}")
    public List<Order> getRestourantOrders(@PathVariable Long restourantId) {
        List<Order> orders = this.orderRepo.findByRestourantId(restourantId);
        if(orders.size() == 0) {
            throw  new ResourceNotFound("orders not found");
        }
        return orders;
    }
}
