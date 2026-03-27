package com.example.commerce;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private final List<Category> categories;
    private final Scanner sc;

    public CommerceSystem(List<Category> categories, Scanner sc) {
        this.categories = categories;
        this.sc = sc;
    }

    public void start() {   // 접근 제어자 설정

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getCategories());
            }
            System.out.println("0. 종료     | 프로그램 종료");

            int choice = getValidatedInput(categories.size(), "유효하지 않은 메뉴 번호입니다!");

            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                return;
            }

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
                continue;
            }

            Product product = category.getProducts().get(productChoice - 1);
            System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개\n\n",
                product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());
        }
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