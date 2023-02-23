package com.example.springsecurityjwttutorial.controllers;


import com.example.springsecurityjwttutorial.entity.Product;
import com.example.springsecurityjwttutorial.entity.Restourant;
import com.example.springsecurityjwttutorial.exceptions.ResourceNotFound;
import com.example.springsecurityjwttutorial.repository.ProductRepo;
import com.example.springsecurityjwttutorial.repository.RestourantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired private ProductRepo productRepo;
    @Autowired private RestourantRepo restourantRepo;

    @PostMapping("{restourantId}")
    public Product createProduct(@RequestBody Product product, @PathVariable Long restourantId) {
        Product saved = this.productRepo.save(product);
        Optional<Restourant> restourant = this.restourantRepo.findById(restourantId);
        if(restourant !=null) saved.setRestourant(restourant.get());
        productRepo.save(saved);
        Restourant updated = restourant.get();
        updated.getProducts().add(saved);
        restourantRepo.save(updated);
        return saved;
    }

    @GetMapping("{restourantId}")
    public List<Product> getRestourantProducts(@PathVariable Long restourantId) {
        Restourant restourant = this.restourantRepo.findById(restourantId).orElseThrow(()->new RuntimeException("restourant not found"));
        List<Product> products = restourant.getProducts();
        if(products.size() == 0) {
            throw new ResourceNotFound("no products found");
        }
        return products;
    }
}
