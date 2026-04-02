package com.example.commerce;

/**
 * 상품 정보를 나타내는 클래스
 */
public class Product {

    private final String productName;
    private final int price;
    private final String description;
    private final int quantity;

    public Product(String productName, int price, String description, int quantity) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }
}