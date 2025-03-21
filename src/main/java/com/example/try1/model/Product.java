package com.example.try1.model;

public class Product {
    private String title;
    private String price;
    private String imageUrl;
    private String productUrl;

    public Product(String title, String price, String imageUrl, String productUrl) {
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
    }

    // Геттеры
    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }


}