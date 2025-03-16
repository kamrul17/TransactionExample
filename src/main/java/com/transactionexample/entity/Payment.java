package com.transactionexample.entity;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private double amount;

    public Payment() {}

    public Payment(String customerName, double amount) {
        this.customerName = customerName;
        this.amount = amount;
    }
}

