package com.example.commerce2;

import java.util.List;

public class CommerceView {

    public void printMainMenu(List<Category> categories, Cart cart) {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        System.out.println("0. 종료     | 프로그램 종료");

        if (!cart.isEmpty()) {
            System.out.println();
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
        }
    }

    public void printCategoryProducts(Category category) {
        System.out.println();
        System.out.println("[ " + category.getCategoryName() + " 카테고리 ]");

        List<Product> products = category.getProducts();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. %-14s | %,9d원 | %s | 재고: %d개%n",
                i + 1,
                product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());
        }
        System.out.println("0. 뒤로가기");
    }

    public void printProductSelect(Product product) {
        System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개%n%n",
            product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());

        System.out.printf("\"%s | %,d원 | %s\"%n",
            product.getProductName(), product.getPrice(), product.getDescription());
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
    }

    public void printCart(Cart cart) {
        System.out.println("[ 장바구니 내역 ]");

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            System.out.printf("%s | %,d원 | %s | 수량: %d개%n",
                product.getProductName(), product.getPrice(), product.getDescription(), item.getQuantity());
        }

        System.out.println();
        System.out.println("[ 총 주문 금액 ]");
        System.out.printf("%,d원%n", cart.getTotalPrice());
        System.out.println();
    }

    public void printOrderSummary(Cart cart) {
        System.out.println();
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();

        printCart(cart);
        System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
    }
}