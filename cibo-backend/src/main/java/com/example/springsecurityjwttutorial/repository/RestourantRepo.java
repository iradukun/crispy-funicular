package com.example.springsecurityjwttutorial.repository;

import com.example.springsecurityjwttutorial.entity.Product;
import com.example.springsecurityjwttutorial.entity.Restourant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestourantRepo extends JpaRepository<Restourant, Long> {

}

