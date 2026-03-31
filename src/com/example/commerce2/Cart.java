package com.example.commerce2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {

    private final List<CartItem> items = new ArrayList<>();

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items); // 불변 객체
    }

    // 장바구니 상품 추가
    public void addProduct(Product product) {
        CartItem cartItem = findCartItem(product);

        if (cartItem == null) {
            if (product.hasInsufficientQuantity(1)) {   // 재고 확인
                throw new IllegalArgumentException("재고가 부족하여 장바구니에 담을 수 없습니다.");
            }

            items.add(new CartItem(product, 1));    // 상품 추가
            return;
        }

        // CartItem에 상품이 존재하면 수량 증가
        int newQuantity = cartItem.getQuantity() + 1;

        if (product.hasInsufficientQuantity(newQuantity)) { // 재고 확인
            throw new IllegalArgumentException("재고가 부족하여 장바구니에 더 담을 수 없습니다.");
        }

        cartItem.increaseQuantity();
    }

    public int getTotalPrice() {
        int total = 0;

        for (CartItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public List<String> order() {
        if (isEmpty()) {
            throw new IllegalArgumentException("장바구니가 비어 있습니다.");
        }

        List<String> messages = new ArrayList<>();

        for (CartItem item : items) {
            Product product = item.getProduct();

            int before = product.getQuantity();
            product.decreaseQuantity(item.getQuantity());
            int after = product.getQuantity();

            messages.add(  // 포맷된 메시지 저장
                String.format("%s 재고가 %d개 → %d개로 업데이트되었습니다.",
                    product.getProductName(), before, after));
        }
        clear();
        return messages;
    }

    public void clear() {
        items.clear();
    }

    public void cancelOrder() {
        clear();
    }

    // 상품이 장바구니에 있는지 확인
    private CartItem findCartItem(Product product) {
        for (CartItem item : items) {
            if (item.getProduct() == product) { // 객체 비교
                return item;
            }
        }
        return null;
    }
}