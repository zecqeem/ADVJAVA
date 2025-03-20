package com.example.try1.controller;

import com.example.try1.service.ProductParserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.try1.model.Product;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductParserService parserService;

    public ProductController(ProductParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return parserService.getProducts();
    }
}