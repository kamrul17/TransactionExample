package com.transactionexample.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class CreditCardPaymentService implements QualiAndPrimaryEx {
    @Override
    public void pay() {
        System.out.println("Paid with Credit Card");
    }
}
