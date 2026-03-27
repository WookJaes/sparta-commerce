package com.example.commerce;

import java.util.List;

public class Category {

    private final List<Product> products;
    private final String categories;


    public Category(List<Product> products, String categories) {
        this.products = products;
        this.categories = categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getCategories() {
        return categories;
    }
}