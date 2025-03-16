package com.transactionexample.entity;

import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String roomType;

    public Booking() {}

    public Booking(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}
