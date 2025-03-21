package com.transactionexample;

import com.transactionexample.entity.Booking;
import com.transactionexample.service.BookingService;
import com.transactionexample.service.OrderService;
import com.transactionexample.service.QualiAndPrimaryEx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;
/*
TRANSACTION:it helps to achieve ACID properties.

A (Atomicity):
Ensures all operations within a transaction are completed successfully. If any operation fails, the entire transaction will get rollback.
C (Consistency):
Ensures that DB state before and after the transactions should be Consistent only.
I (Isolation):
Ensures that, even if multiple transactions are running in parallel, they do not interfere with each other.
Durability:
Ensures that committed transaction will never lost despite system failure or crash.

Transaction:
  1.Class level:Transaction only applicable to public method inside class
  2.Method level : Transaction only applicable to public method levels
=================PROPAGATION
Imagine you're working on a group project where Person A (Main Method) is responsible
 for starting the project, and they ask Person B (Called Method) for help.

REQUIRED → Person B joins Person A in the same work session if no transaction available then create one.
REQUIRES_NEW → Person B starts a new, independent session.
SUPPORTS →I don’t care whether there's a transaction or not.
👉 If there’s already a transaction → I’ll join it.
👉 If there’s no transaction → I’ll happily run without it!
NOT_SUPPORT-> If there's a transaction running → Suspend (pause) it!
👉 Run the method WITHOUT any transaction.
MANDATORY → Person B will only work if A has already started; otherwise, they refuse.
NEVER → Person B refuses to work if A is already working.
 NESTED -> If the nested transaction fails, you can roll back only the nested part, while keeping the outer transaction intact!
However, if the outer transaction fails, everything (including nested) is rolled back.
So, propagation controls whether a new transaction starts or the existing one is used. 🚀
*
*
* */

// Scenario: E-commerce Order with Multiple Items
//
//You have a transaction for placing an order with multiple items.
//Each item stock update happens inside a nested transaction.
//If one item fails (out of stock), you want to:
//
//Rollback only that item's stock update, not the entire order immediately.
//Allow the main transaction to decide whether to proceed or fail later.

@SpringBootApplication
//@EnableScheduling
public class TransactionexampleApplication {

	public static void main(String[] args) {

		 ConfigurableApplicationContext context = SpringApplication.run(TransactionexampleApplication.class, args);
//		 BookingService bean = context.getBean(BookingService.class);
//		 bean.bookHotel("Fam",12000);

//		List<Long> itemIds = Arrays.asList(1L, 2L, 3L, 4L);
//		 OrderService orderService = context.getBean(OrderService.class);
//		orderService.placeOrder(itemIds);

//		Example of PRIMARY AND QUALIFIER
//		 QualiAndPrimaryEx bean = context.getBean(QualiAndPrimaryEx.class);
//		 bean.pay();
		QualiAndPrimaryEx bean = context.getBean(QualiAndPrimaryEx.class);

	}

}
