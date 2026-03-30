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
        return Collections.unmodifiableList(items); // 불변 객체
    }

    // 장바구니 상품 추가
    public void addProduct(Product product) {
        CartItem cartItem = findItem(product);

        if (cartItem == null) {
            if (!product.hasEnoughStock(1)) {   // 재고 확인
                throw new IllegalArgumentException("재고가 부족하여 장바구니에 담을 수 없습니다.");
            }

            items.add(new CartItem(product, 1));    // 상품 추가
            return;
        }

        // CartItem에 상품이 존재하면 수량 증가
        int newQuantity = cartItem.getQuantity() + 1;

        if (!product.hasEnoughStock(newQuantity)) { // 재고 확인
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

    public void order() {
        if (isEmpty()) {
            throw new IllegalArgumentException("장바구니가 비어 있습니다.");
        }

        int totalPrice = getTotalPrice();
        System.out.println();
        System.out.printf("주문이 완료되었습니다! 총 금액: %,d원%n", totalPrice);

        for (CartItem item : items) {
            Product product = item.getProduct();

            int before = product.getQuantity();
            product.decreaseQuantity(item.getQuantity());
            int after = product.getQuantity();

            System.out.printf("%s 재고가 %d개 → %d개로 업데이트되었습니다.%n",
                product.getProductName(), before, after);
        }
        clear();
    }

    public void clear() {
        items.clear();
    }

    public void cancelOrder() {
        clear();
        System.out.println("주문이 취소되었습니다.");
    }

    public void printCart() {
        System.out.println("[ 장바구니 내역 ]");

        for (CartItem item : items) {
            Product product = item.getProduct();

            System.out.printf("%s | %,d원 | %s | 수량: %d개%n",
                product.getProductName(), product.getPrice(), product.getDescription(), item.getQuantity());
        }

        System.out.println();
        System.out.println("[ 총 주문 금액 ]");
        System.out.printf("%,d원%n", getTotalPrice());
        System.out.println();
    }

    // 상품이 장바구니에 있는지 확인
    private CartItem findItem(Product product) {
        for (CartItem item : items) {
            if (item.getProduct() == product) { // 객체 비교
                return item;
            }
        }
        return null;
    }
}