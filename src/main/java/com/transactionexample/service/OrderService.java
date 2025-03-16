package com.transactionexample.service;

import com.transactionexample.entity.OrderTable;
import com.transactionexample.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockService stockService;

    @Transactional
    public void placeOrder( List<Long> itemIds) {
        // Save order details
        OrderTable order = new OrderTable( "NEW");
        orderRepository.save(order);

        for (Long itemId : itemIds) {
            try {
                // Nested transaction for each item stock update
                stockService.updateStock(itemId);
            } catch (Exception e) {
                System.out.println("Stock update failed for item: " + itemId + ", continuing...");
            }
        }

        // At this point, order can proceed, even if some items failed.
    }
}
