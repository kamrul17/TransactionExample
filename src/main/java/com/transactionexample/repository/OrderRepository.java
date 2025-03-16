package com.transactionexample.repository;

import com.transactionexample.entity.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderTable,Long> {
}
