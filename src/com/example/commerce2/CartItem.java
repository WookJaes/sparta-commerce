package com.example.commerce2;

/**
 * 장바구니에 담긴 상품과 수량을 나타내는 클래스
 */
public class CartItem {

    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }
}