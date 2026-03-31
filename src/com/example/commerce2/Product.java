package com.example.commerce2;

public class Product {

    private final String productName;
    private final int price;
    private final String description;
    private int quantity;   // 재고 변경을 위해 final 삭제

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

    public boolean hasInsufficientQuantity(int amount) {
        return quantity < amount;
    }

    public void decreaseQuantity(int amount) {
        if (hasInsufficientQuantity(amount)) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        quantity -= amount;
    }
}