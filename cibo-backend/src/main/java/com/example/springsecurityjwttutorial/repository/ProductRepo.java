package com.example.springsecurityjwttutorial.repository;

import com.example.springsecurityjwttutorial.entity.Product;
import com.example.springsecurityjwttutorial.entity.Restourant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
