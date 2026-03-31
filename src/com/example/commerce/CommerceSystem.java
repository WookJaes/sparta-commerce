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

    public void start() {
        while (true) {
            try {
                printMainMenu();

                int choice = getValidatedInput(
                    cart.isEmpty() ? categories.size() : categories.size() + 2,
                    "유효하지 않은 메뉴 번호입니다!");

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

    private boolean executeOrderMenu(int choice) {
        if (!cart.isEmpty() && choice == 4) {
            System.out.println();
            System.out.println("아래와 같이 주문 하시겠습니까?");
            System.out.println();

            printCart();
            System.out.println("1. 주문 확정      2. 메인으로 돌아가기");

            int orderChoice = getValidatedInput(2, "유효하지 않은 메뉴 번호입니다!");

            if (orderChoice == 1) {
                int totalPrice = cart.getTotalPrice();
                List<String> messages = cart.order();

                System.out.printf("주문이 완료되었습니다! 총 금액: %,d원%n", totalPrice);
                for (String message : messages) {
                    System.out.println(message);
                }
            }

            System.out.println();
            return true;
        }

        if (!cart.isEmpty() && choice == 5) {
            cart.cancelOrder();
            System.out.println("주문이 취소되었습니다.");
            System.out.println();
            return true;
        }
        return false;
    }

    // 카테고리 선택 후 상품 조회 및 장바구니 추가
    private void executeCategoryMenu(int choice) {
        Category category = categories.get(choice - 1);
        printCategoryProducts(category);

        int productChoice = getValidatedInput(category.getProducts().size(), "유효하지 않은 상품 번호입니다!");

        if (productChoice == 0) {
            System.out.println();
            return;
        }

        Product product = category.getProducts().get(productChoice - 1);
        handleAddToCart(product);
    }

    private void printCategoryProducts(Category category) {
        System.out.println();
        System.out.println("[ " + category.getCategoryName() + " 카테고리 ]");

        List<Product> products = category.getProducts();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. %-14s | %,9d원 | %s | 재고: %d개%n", i + 1,
                product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());
        }
        System.out.println("0. 뒤로가기");
    }

    // 상품을 장바구니에 추가할지 사용자에게 확인하고 처리
    private void handleAddToCart(Product product) {
        System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개\n\n",
            product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());

        System.out.printf("\"%s | %,d원 | %s\"%n", product.getProductName(), product.getPrice(), product.getDescription());
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        int addChoice = getValidatedInput(2, "유효하지 않은 메뉴 번호입니다!");

        if (addChoice == 1) {
            cart.addProduct(product);
            System.out.println();
            System.out.println(product.getProductName() + "가 장바구니에 추가되었습니다.");
        }

        System.out.println();
        System.out.println("아래 메뉴를 선택해주세요.");
        System.out.println();
    }

    // 장바구니 내역 출력
    private void printCart() {
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