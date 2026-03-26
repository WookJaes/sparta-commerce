package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product product1 = new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10);
        Product product2 = new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 15);
        Product product3 = new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 3);
        Product product4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 8);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

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