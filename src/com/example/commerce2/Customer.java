package com.example.commerce2;

public class Customer {

    private final String customerName;
    private final String email;
    private final String grade;

    public Customer(String customerName, String email, String grade) {
        this.customerName = customerName;
        this.email = email;
        this.grade = grade;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getGrade() {
        return grade;
    }
}