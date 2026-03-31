package com.example.commerce;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private final List<Category> categories;
    private final Scanner sc;
    private final Cart cart;
    private final CommerceView CommerceView;

    public CommerceSystem(List<Category> categories, Scanner sc) {
        this.categories = categories;
        this.sc = sc;
        this.cart = new Cart();
        this.CommerceView = new CommerceView();
    }

    public void start() {
        while (true) {
            try {
                CommerceView.printMainMenu(categories, cart);

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

    private boolean executeOrderMenu(int choice) {
        if (!cart.isEmpty() && choice == 4) {
            System.out.println();
            System.out.println("아래와 같이 주문 하시겠습니까?");
            System.out.println();

            CommerceView.printCart(cart);
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

    private void executeCategoryMenu(int choice) {
        Category category = categories.get(choice - 1);
        CommerceView.printCategoryProducts(category);

        int productChoice = getValidatedInput(category.getProducts().size(), "유효하지 않은 상품 번호입니다!");

        if (productChoice == 0) {
            System.out.println();
            return;
        }

        Product product = category.getProducts().get(productChoice - 1);
        handleAddToCart(product);
    }

    private void handleAddToCart(Product product) {
        CommerceView.printProductSelect(product);

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