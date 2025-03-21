package com.example.try1.controller;
import com.example.try1.service.CurrencyService;
import com.example.try1.service.ExcelService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
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
        // Генерируем файл и сохраняем его в временной папке
        ProductParserService productParserService=new ProductParserService();
        List<Product> products = productParserService.getProducts();
        CurrencyService currencyService = new CurrencyService();
        ExcelService excelService = new ExcelService();
        String usdRate = currencyService.getUsdRate();
        File file = excelService.saveProductsToExcel(products, usdRate); // Генерация и сохранение файла

        // Загружаем файл для отдачи на скачивание
        Resource resource = new FileSystemResource(file);

        // Отправляем файл на скачивание
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=products.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    @GetMapping("/")
    public String index() {
        return "index"; // Файл index.html в папке templates
    }
}