package com.example.commerce1;

import java.util.Collections;
import java.util.List;

/**
 * 상품 카테고리 클래스
 */
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
}