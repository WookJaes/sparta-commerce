package com.example.commerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {

    private final List<CartItem> items = new ArrayList<>();

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}