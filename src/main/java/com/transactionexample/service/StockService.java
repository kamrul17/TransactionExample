package com.transactionexample.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    @Transactional(propagation = Propagation.NESTED)
    public void updateStock(Long itemId) {
        System.out.println("Updating stock for item: " + itemId);
        if (itemId % 2 == 0) { // Simulate failure for even item IDs
            throw new RuntimeException("Stock not available for item: " + itemId);
        }
        // Otherwise, update stock normally
        // e.g., decrease stock quantity
    }
}
