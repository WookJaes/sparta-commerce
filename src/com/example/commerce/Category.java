package com.example.commerce;

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
}