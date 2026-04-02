package com.example.commerce2;

import java.util.List;

/**
 * 사용자 화면(출력)을 담당하는 클래스
 */
public class CommerceView {

    // 메인 메뉴 출력
    public void printMainMenu(List<Category> categories, Cart cart) {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

        // 카테고리 목록 출력
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        System.out.println("0. 종료     | 프로그램 종료");
        System.out.println("6. 관리자 모드");

        // 장바구니가 있을 때 주문 메뉴 출력
        if (!cart.isEmpty()) {
            System.out.println();
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
        }
    }

    // 카테고리 목록 출력
    public void printCategoryProducts(Category category) {
        System.out.println();
        System.out.println("[ " + category.getCategoryName() + " 카테고리 ]");

        List<Product> products = category.getProducts();

        // 상품 정보 출력
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. %-14s | %,9d원 | %s | 재고: %d개%n", i + 1,
                product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());
        }
        System.out.println("0. 뒤로가기");
    }

    // 선택한 상품 상세 출력
    public void printProductSelect(Product product) {
        System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개%n%n",
            product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());

        System.out.printf("\"%s | %,d원 | %s\"%n",
            product.getProductName(), product.getPrice(), product.getDescription());
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
    }

    // 장바구니 목록 출력
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

    // 주문 확인 화면 출력
    public void printOrderSummary(Cart cart) {
        System.out.println();
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();

        printCart(cart);
        System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
    }

    // 관리자 메뉴 출력
    public void printAdminMenu() {
        System.out.println("[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
    }

    // 상품 추가 시 카테고리 선택 화면 출력
    public void printCategorySelection(List<Category> categories) {
        System.out.println();
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
    }

    // 단일 상품 정보 출력
    public void printProductInfo(Product product) {
        System.out.printf("%s | %,d원 | %s | 재고: %d개%n",
            product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());
    }

    // 상품 수정 항목 선택 메뉴 출력
    public void printEditMenu() {
        System.out.println("수정할 항목을 선택해주세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");
    }

    // 전체 카테고리의 상품 목록 출력
    public void printAllProducts(List<Category> categories) {
        System.out.println("[ 전체 상품 현황 ]");

        for (Category category : categories) {
            System.out.println();
            System.out.println("[ " + category.getCategoryName() + " ]");

            for (Product product : category.getProducts()) {
                System.out.printf("%s | %,d원 | %s | 재고: %d개%n",
                    product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());
            }
        }
        System.out.println();
    }

    public void printAddProductHeader(Category category) {
        System.out.println();
        System.out.println("[ " + category.getCategoryName() + " 카테고리에 상품 추가 ]");
    }

    public void printAddMessage() {
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
    }

    public void printDeleteMessage() {
        System.out.println("위 상품을 삭제하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
    }
}