package com.example.commerce;

import java.util.Collections;
import java.util.List;

public class Category {

    private final List<Product> products;
    private final String categories;


    public Category(List<Product> products, String categories) {
        this.products = products;
        this.categories = categories;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);  // 캡슐화를 위해 불변 리스트로 반환
    }

    public String getCategories() {
        return categories;
    }
}