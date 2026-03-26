package com.example.commerce;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private final List<Product> products;

    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.printf("%d. %-14s | %,9d원 | %s\n", i + 1,
                    product.getProductName(), product.getPrice(), product.getDescription());
            }
            System.out.println("0. 종료           | 프로그램 종료");

            int choice = sc.nextInt();
            if (choice == 0) {
                System.out.println("\n커머스 플랫폼을 종료합니다.");
                return;
            }
        }
    }
}