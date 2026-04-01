package com.example.commerce2;

import java.util.Collections;
import java.util.List;

public class Category {

    private final List<Product> products;
    private final String categoryName;


    public Category(List<Product> products, String categoryName) {
        this.products = products;
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);  // 캡슐화를 위해 불변 리스트로 반환
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void addProduct(Product product) {   // 상품 추가
        if (product == null) {
            throw new IllegalArgumentException("추가할 상품이 없습니다.");
        }
        products.add(product);
    }

    public void removeProduct(Product product) {    // 상품 삭제
        if (product == null) {
            throw new IllegalArgumentException("삭제할 상품이 없습니다.");
        }

        boolean removed = products.remove(product);

        if (!removed) {
            throw new IllegalArgumentException("해당 상품이 카테고리에 존재하지 않습니다.");
        }
    }

    // 카테고리 내 동일한 상품명이 존재하는지 확인
    public boolean hasProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명을 입력해주세요.");
        }

        String trimmedName = productName.trim();

        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(trimmedName)) {
                return true;
            }
        }
        return false;
    }

    // 카테고리에서 상품명을 기준으로 조회
    public Product findProductByName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명을 입력해주세요.");
        }

        String trimmedName = productName.trim();

        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(trimmedName)) {
                return product;
            }
        }
        return null;
    }
}