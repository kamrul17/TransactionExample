package com.transactionexample.entity;

import jakarta.persistence.*;

@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String failureReason;

    public AuditLog() {}

    public AuditLog(String customerName, String failureReason) {
        this.customerName = customerName;
        this.failureReason = failureReason;
    }
}

