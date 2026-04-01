package com.example.commerce2;

import java.util.List;
import java.util.Scanner;

public class AdminService {

    private static final String ADMIN_PASSWORD = "admin123";

    private final List<Category> categories;
    private final Scanner sc;
    private final Cart cart;
    private final CommerceView commerceView;

    public AdminService(List<Category> categories, Scanner sc, Cart cart,
        CommerceView commerceView) {
        this.categories = categories;
        this.sc = sc;
        this.cart = cart;
        this.commerceView = commerceView;
    }

    public void runAdminMenu() {
        if (!authenticate()) {
            return;
        }

        while (true) {
            commerceView.printAdminMenu();
            int choice = getValidatedInput(4, "유효하지 않은 메뉴 번호입니다!");

            if (choice == 0) {
                System.out.println();
                return;
            }

            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        editProduct();
                        break;
                    case 3:
                        removeProduct();
                        break;
                    case 4:
                        commerceView.printAllProducts(categories);
                        break;
                    default:
                        throw new IllegalArgumentException("유효하지 않은 메뉴 번호입니다!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private boolean authenticate() {
        int failCount = 0;

        while (failCount < 3) {
            System.out.println();
            System.out.print("관리자 비밀번호를 입력해주세요: ");
            String input = sc.nextLine();

            if (ADMIN_PASSWORD.equals(input)) {
                System.out.println();
                return true;
            }

            failCount++;
            System.out.println("비밀번호가 일치하지 않습니다.");

            if (failCount == 3) {
                System.out.println("비밀번호 입력 3회 실패로 메인 메뉴로 돌아갑니다.");
                System.out.println();
                return false;
            }
        }

        return false;
    }

    private void addProduct() {
        commerceView.printCategorySelection(categories);
        int categoryChoice = getValidatedInput(categories.size(), "유효하지 않은 카테고리 번호입니다!");

        if (categoryChoice == 0) {
            System.out.println();
            return;
        }

        Category category = categories.get(categoryChoice - 1);
        commerceView.printAddProductHeader(category);

        String productName = readString("상품명을 입력해주세요: ");

        if (category.hasProductName(productName)) {
            throw new IllegalArgumentException("같은 카테고리 내에 동일한 상품명이 이미 존재합니다.");
        }

        int price = readAmount("가격을 입력해주세요: ");
        String description = readString("상품 설명을 입력해주세요: ");
        int quantity = readAmount("재고수량을 입력해주세요: ");

        Product newProduct = new Product(productName, price, description, quantity);

        System.out.println();
        commerceView.printProductInfo(newProduct);
        commerceView.printAddMessage();

        int confirm = getValidatedInput(2, "유효하지 않은 메뉴 번호입니다!");

        if (confirm == 1) {
            category.addProduct(newProduct);
            System.out.println();
            System.out.println("상품이 성공적으로 추가되었습니다!");
        } else {
            System.out.println("상품 추가가 취소되었습니다.");
        }

        System.out.println();
    }

    private void editProduct() {
        Product product = findProductByName();

        System.out.print("현재 상품 정보: ");
        commerceView.printProductInfo(product);
        System.out.println();

        commerceView.printEditMenu();
        int editChoice = getValidatedInput(3, "유효하지 않은 메뉴 번호입니다!");
        System.out.println();

        switch (editChoice) {
            case 1:
                updatePrice(product);
                break;
            case 2:
                updateDescription(product);
                break;
            case 3:
                updateQuantity(product);
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 메뉴 번호입니다!");
        }

        System.out.println();
    }

    private void updatePrice(Product product) {
        int oldPrice = product.getPrice();
        System.out.printf("현재 가격: %,d원%n", oldPrice);

        int newPrice = readAmount("새로운 가격을 입력해주세요: ");
        product.setPrice(newPrice);

        System.out.printf("%n%s의 가격이 %,d원 → %,d원으로 수정되었습니다.%n",
            product.getProductName(), oldPrice, product.getPrice());
    }

    private void updateDescription(Product product) {
        String oldDescription = product.getDescription();
        System.out.println("현재 설명: " + oldDescription);

        String newDescription = readString("새로운 설명을 입력해주세요: ");
        product.setDescription(newDescription);

        System.out.printf("%n%s의 설명이 \"%s\" → \"%s\"로 수정되었습니다.%n",
            product.getProductName(), oldDescription, product.getDescription());
    }

    private void updateQuantity(Product product) {
        int oldQuantity = product.getQuantity();
        System.out.printf("현재 재고수량: %d개%n", oldQuantity);

        int newQuantity = readAmount("새로운 재고수량을 입력해주세요: ");
        product.setQuantity(newQuantity);

        System.out.printf("%n%s의 재고수량이 %d개 → %d개로 수정되었습니다.%n",
            product.getProductName(), oldQuantity, product.getQuantity());
    }

    private void removeProduct() {
        System.out.println();
        String productName = readString("삭제할 상품명을 입력해주세요: ");

        Category targetCategory = null;
        Product targetProduct = null;

        for (Category category : categories) {
            Product foundProduct = category.findProductByName(productName);
            if (foundProduct != null) {
                targetCategory = category;
                targetProduct = foundProduct;
                break;
            }
        }

        if (targetProduct == null) {
            throw new IllegalArgumentException("해당 상품을 찾을 수 없습니다.");
        }

        commerceView.printProductInfo(targetProduct);
        System.out.println();
        commerceView.printDeleteMessage();

        int confirm = getValidatedInput(2, "유효하지 않은 메뉴 번호입니다!");

        if (confirm == 1) {
            targetCategory.removeProduct(targetProduct);
            cart.removeProduct(targetProduct);
            System.out.println();
            System.out.println("상품이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("상품 삭제가 취소되었습니다.");
        }

        System.out.println();
    }

    private Product findProductByName() {
        System.out.println();
        String productName = readString("수정할 상품명을 입력해주세요: ");

        for (Category category : categories) {
            Product product = category.findProductByName(productName);
            if (product != null) {
                return product;
            }
        }

        throw new IllegalArgumentException("해당 상품을 찾을 수 없습니다.");
    }

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

    private String readString(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("값을 입력해주세요.");
        }
    }

    private int readAmount(String message) {
        while (true) {
            System.out.print(message);

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("숫자를 입력해주세요!");
                continue;
            }

            try {
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("1 이상의 숫자를 입력해주세요.");

            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요!");
            }
        }
    }
}