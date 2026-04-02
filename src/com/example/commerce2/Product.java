package com.example.commerce2;

/**
 * 상품 정보를 나타내는 클래스
 */
public class Product {

    private final String productName;
    private int price;
    private String description;
    private int quantity;

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

    // 재고 확인
    public boolean hasInsufficientQuantity(int amount) {
        return quantity < amount;
    }

    // 재고 감소
    public void decreaseQuantity(int amount) {
        if (hasInsufficientQuantity(amount)) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        quantity -= amount;
    }

    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("가격은 1 이상이어야 합니다.");
        }
        this.price = price;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("상품 설명을 입력해주세요.");
        }
        this.description = description;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("재고수량은 1 이상이어야 합니다.");
        }
        this.quantity = quantity;
    }
}