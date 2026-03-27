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

    void start() {

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getCategories());
            }
            System.out.println("0. 종료     | 프로그램 종료");

            int choice = sc.nextInt();
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

            int productChoice = sc.nextInt();
            if (productChoice == 0) {
                System.out.println();
                continue;
            }

            Product product = category.getProducts().get(productChoice - 1);
            System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개\n\n",
                product.getProductName(), product.getPrice(), product.getDescription(), product.getQuantity());
        }
    }
}