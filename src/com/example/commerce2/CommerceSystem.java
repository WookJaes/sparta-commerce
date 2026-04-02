package com.example.commerce2;

import java.util.List;
import java.util.Scanner;

/**
 * 커머스 플랫폼의 전체 흐름을 제어하는 클래스
 */
public class CommerceSystem {

    private final List<Category> categories;
    private final Scanner sc;
    private final Cart cart;
    private final CommerceView commerceView;
    private final AdminService adminService;

    public CommerceSystem(List<Category> categories, Scanner sc) {
        this.categories = categories;
        this.sc = sc;
        this.cart = new Cart();
        this.commerceView = new CommerceView();
        this.adminService = new AdminService(categories, sc, cart, commerceView);
    }

    public void start() {
        while (true) {
            commerceView.printMainMenu(categories, cart);   // 메인 메뉴 출력

            int choice = getMainMenuInput();    // 메인 메뉴 입력값 처리

            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                return;
            }

            try {
                if (choice == 6) {
                    adminService.runAdminMenu();
                    continue;
                }

                // 주문 및 주문 취소 메뉴 메서드
                if (executeOrderMenu(choice)) {
                    continue;
                }

                // 카테고리 메뉴 로직 메서드
                executeCategoryMenu(choice);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    // 주문 및 주문 취소 메뉴 처리
    private boolean executeOrderMenu(int choice) {
        if (!cart.isEmpty() && choice == 4) {
            commerceView.printOrderSummary(cart);   // 주문 요약 화면 출력

            // 사용자 입력 검증
            int orderChoice = getValidatedInput(2, "유효하지 않은 메뉴 번호입니다!");

            if (orderChoice == 1) {
                int totalPrice = cart.getTotalPrice();
                List<String> messages = cart.order();

                System.out.println();
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

    // 카테고리 선택 후 상품 목록 처리
    private void executeCategoryMenu(int choice) {
        Category category = categories.get(choice - 1);
        commerceView.printCategoryProducts(category);

        // 사용자 입력 검증
        int productChoice = getValidatedInput(category.getProducts().size(), "유효하지 않은 상품 번호입니다!");

        // 뒤로 가기 기능
        if (productChoice == 0) {
            System.out.println();
            return;
        }

        Product product = category.getProducts().get(productChoice - 1);
        handleAddToCart(product);   // 장바구니 추가
    }

    // 상품 선택 후 장바구니 추가 처리
    private void handleAddToCart(Product product) {
        commerceView.printProductSelect(product);

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

    // 메인 메뉴 전용 입력 처리
    private int getMainMenuInput() {
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("숫자를 입력해주세요!");
                sc.next();
                continue;
            }

            int input = sc.nextInt();
            sc.nextLine();

            if (input == 0 || input == 6) {
                return input;
            }

            if (input >= 1 && input <= categories.size()) {
                return input;
            }

            if (!cart.isEmpty() && (input == 4 || input == 5)) {
                return input;
            }

            System.out.println("유효하지 않은 메뉴 번호입니다!");
        }
    }

    // 사용자 입력 검증 (재사용 가능)
    private int getValidatedInput(int indexSize, String errorMessage) {
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("숫자를 입력해주세요!");
                sc.next();
                continue;
            }

            int input = sc.nextInt();
            sc.nextLine();

            if (input < 0 || input > indexSize) {
                System.out.println(errorMessage);
                continue;
            }

            return input;
        }
    }
}