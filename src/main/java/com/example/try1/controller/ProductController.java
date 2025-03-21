package com.example.try1.controller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import com.example.try1.service.ProductParserService;
import com.example.try1.model.Product;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductParserService parserService;

    public ProductController(ProductParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return parserService.getProducts();
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadExcel() throws IOException {
        // Загружаем файл из ресурсов (работает и в JAR)
        ClassPathResource resource = new ClassPathResource("products.xlsx");

        // Конвертируем в InputStreamResource для ResponseEntity
        InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=products.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }
    @GetMapping("/")
    public String index() {
        return "index"; // Файл index.html в папке templates
    }
}