package com.transactionexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
//    @Autowired
//    private QualiAndPrimaryEx paypal;

//    @Autowired
//    @Qualifier("creditCardPaymentService")
    private QualiAndPrimaryEx creditcard;

    public PaymentService(@Qualifier("creditCardPaymentService") QualiAndPrimaryEx creditcard) {
        this.creditcard = creditcard;
    }


    public void doPay() {
        creditcard.pay();        // Output: Car started

    }
}
