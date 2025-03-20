package com.example.try1.service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.example.try1.model.Product;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    // Метод для сохранения продуктов в Excel
    public void saveProductsToExcel(List<Product> products,float currency) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");

        // Создаём заголовки столбцов
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Название");
        header.createCell(1).setCellValue("Цена");
        header.createCell(2).setCellValue("Изображение");
        header.createCell(3).setCellValue("Ссылка");
        header.createCell(4).setCellValue("Курс");

        // Заполняем данными
        int rowNum = 1;
        for (Product product : products) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(product.getTitle());
            row.createCell(1).setCellValue(product.getPrice());
            row.createCell(2).setCellValue(product.getImageUrl());
            row.createCell(3).setCellValue(product.getProductUrl());
            row.createCell(4).setCellValue(currency);
        }

        // Сохраняем файл
        try (FileOutputStream fileOut = new FileOutputStream(new File("products.xlsx"))) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("📥 Данные сохранены в файл products.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}