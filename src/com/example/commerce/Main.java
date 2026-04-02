package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 커머스 플랫폼 실행 클래스
 */
public class Main {

    public static void main(String[] args) {
        List<Product> device = new ArrayList<>();
        device.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        device.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 15));
        device.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 3));
        device.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 8));

        List<Product> clothes = new ArrayList<>();
        clothes.add(new Product("티셔츠", 15000, "흰색 티셔츠", 40));
        clothes.add(new Product("니트", 25000, "그레이 풀오버 니트", 20));
        clothes.add(new Product("데님 팬츠", 40000, "블랙 와이드 데님 팬츠 ", 10));

        List<Product> food = new ArrayList<>();
        food.add(new Product("양념 치킨", 22000, "매콤 양념 치킨", 20));
        food.add(new Product("불고기 버거", 10000, "한우로 만든 햄버거", 30));
        food.add(new Product("콤비네이션 피자", 30000, "미국식 콤비네이션 피자", 10));

        Category electronics = new Category(device, "전자제품");
        Category clothing = new Category(clothes, "의류");
        Category foods = new Category(food, "식품");

        List<Category> categories = new ArrayList<>();
        categories.add(electronics);
        categories.add(clothing);
        categories.add(foods);

        Scanner sc = new Scanner(System.in);
        CommerceSystem commerceSystem = new CommerceSystem(categories, sc);
        commerceSystem.start();
    }
}