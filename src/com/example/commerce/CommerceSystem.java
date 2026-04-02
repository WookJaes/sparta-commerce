package com.example.commerce;

import java.util.List;
import java.util.Scanner;

/**
 * 커머스 플랫폼의 흐름을 담당하는 클래스
 */
public class CommerceSystem {

    private final List<Category> categories;
    private final Scanner sc;

    public CommerceSystem(List<Category> categories, Scanner sc) {
        this.categories = categories;
        this.sc = sc;
    }

    public void start() {

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

            // 카테고리 목록 출력
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
            }
            System.out.println("0. 종료     | 프로그램 종료");

            // 사용자 입력 검증
            int choice = getValidatedInput(categories.size(), "유효하지 않은 메뉴 번호입니다!");

            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                return;
            }

            // 선택한 카테고리 조회
            Category category = categories.get(choice - 1);
            System.out.println();
            System.out.println("[ " + category.getCategoryName() + " 카테고리 ]");

            // 상품 목록 출력
            for (int i = 0; i < category.getProducts().size(); i++) {
                Product product = category.getProducts().get(i);
                System.out.printf("%d. %-14s | %,9d원 | %s\n", i + 1,
                    product.getProductName(), product.getPrice(), product.getDescription());
            }
            System.out.println("0. 뒤로가기");

            int productChoice = getValidatedInput(category.getProducts().size(), "유효하지 않은 상품 번호입니다!");

            // 뒤로 가기 기능
            if (productChoice == 0) {
                System.out.println();
                continue;
            }

            Product product = category.getProducts().get(productChoice - 1);
            System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개\n\n",
                product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());
        }
    }

    // 사용자 입력 검증
    private int getValidatedInput(int indexSize, String errorMessage) {
        while (true) {
            // 숫자가 아닌 입력 처리
            if (!sc.hasNextInt()) {
                System.out.println("숫자를 입력해주세요!");
                sc.next();
                continue;
            }

            int input = sc.nextInt();

            // 범위 검증
            if (input < 0 || input > indexSize) {
                System.out.println(errorMessage);
                continue;
            }

            return input;
        }
    }
}