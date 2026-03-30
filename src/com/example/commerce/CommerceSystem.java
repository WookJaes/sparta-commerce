package com.example.commerce;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private final List<Category> categories;
    private final Scanner sc;
    private final Cart cart;

    public CommerceSystem(List<Category> categories, Scanner sc) {
        this.categories = categories;
        this.sc = sc;
        this.cart = new Cart();     // CommerceSystem 에서만 사용하는 객체 (매개변수 x)
    }

    public void start() {   // 접근 제어자 설정

        while (true) {
            try {
                printMainMenu();

                int choice = getValidatedInput(
                    cart.isEmpty() ? categories.size() : categories.size() + 2, "유효하지 않은 메뉴 번호입니다!");

                if (choice == 0) {
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    return;
                }

                if (executeOrderMenu(choice)) {
                    continue;
                }

                executeCategoryMenu(choice);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private void printMainMenu() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategories());
        }
        System.out.println("0. 종료     | 프로그램 종료");

        if (!cart.isEmpty()) {
            System.out.println();
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
        }
    }

    private boolean executeOrderMenu(int choice) {
        if (!cart.isEmpty() && choice == 4) {
            System.out.println();
            System.out.println("아래와 같이 주문 하시겠습니까?");
            System.out.println();

            cart.printCart();
            System.out.println("1. 주문 확정      2. 메인으로 돌아가기");

            int orderChoice = getValidatedInput(2, "유효하지 않은 메뉴 번호입니다!");

            if (orderChoice == 1) {
                cart.order();
            }

            System.out.println();
            return true;
        }

        if (!cart.isEmpty() && choice == 5) {
            cart.cancelOrder();
            System.out.println();
            return true;
        }
        return false;
    }

    private void executeCategoryMenu(int choice) {
        Category category = categories.get(choice - 1);
        System.out.println();
        System.out.println("[ " + category.getCategories() + " 카테고리 ]");

        for (int i = 0; i < category.getProducts().size(); i++) {
            Product product = category.getProducts().get(i);
            System.out.printf("%d. %-14s | %,9d원 | %s\n", i + 1,
                product.getProductName(), product.getPrice(), product.getDescription());
        }
        System.out.println("0. 뒤로가기");

        int productChoice = getValidatedInput(category.getProducts().size(), "유효하지 않은 상품 번호입니다!");

        if (productChoice == 0) {
            System.out.println();
            return;
        }

        Product product = category.getProducts().get(productChoice - 1);
        System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개\n\n",
            product.getProductName(), product.getPrice(), product.getDescription(),
            product.getQuantity());

        System.out.printf("\"%s | %,d원 | %s\"%n", product.getProductName(),
            product.getPrice(), product.getDescription());
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        int addChoice = getValidatedInput(2, "유효하지 않은 메뉴 번호입니다!");

        if (addChoice == 1) {
            cart.addProduct(product);
            System.out.println();
            System.out.println(product.getProductName() + "가 장바구니에 추가되었습니다.");
        }

        System.out.println();
    }

    // 입력값 검증 로직을 메서드로 추출
    private int getValidatedInput(int indexSize, String errorMessage) {
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("숫자를 입력해주세요!");
                sc.next();
                continue;
            }

            int input = sc.nextInt();

            if (input < 0 || input > indexSize) {
                System.out.println(errorMessage);
                continue;
            }

            return input;
        }
    }
}