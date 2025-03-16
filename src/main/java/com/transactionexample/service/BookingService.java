package com.transactionexample.service;

import com.transactionexample.entity.Booking;
import com.transactionexample.entity.Payment;
import com.transactionexample.repository.AuditLogRepository;
import com.transactionexample.repository.BookingRepository;
import com.transactionexample.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Transactional
    public void bookHotel(String customerName, double amount) {
        try {
            // Step 1: Save Booking Info
            Booking booking = new Booking(customerName, "Luxury Room");
            bookingRepository.save(booking);

            // Step 2: Process Payment
            Payment payment = new Payment(customerName, amount);
            paymentRepository.save(payment);

            // Simulating an error to test rollback
            if (amount > 5000) {
                throw new RuntimeException("Payment amount too high! Rolling back...");
            }
        }catch (Exception e){
            auditLogService.logFailure(customerName,"Payment amount too high! Rolling back...");
            throw e;

        }

    }
}
