package com.transactionexample.isolationservice;

import com.transactionexample.entity.Product;
import com.transactionexample.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommitted(Long id) {
        System.out.println("READ_UNCOMMITTED: " + productRepository.findById(id).get());
        sleep(19000); // To keep txn open
        System.out.println("READ_UNCOMMITTED: " + productRepository.findById(id).get());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommitted(Long id) {
        System.out.println("Reading product...");

        Product product = productRepository.findById(id).get();
        System.out.println("First Read: " + product);

        // Sleep to allow another transaction to update
        try {
            Thread.sleep(10000); // 10 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Force reload from DB to bypass Hibernate cache
        entityManager.refresh(product);
        System.out.println("Second Read (after refresh): " + product);


    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableRead(Long id) {
        System.out.println("REPEATABLE_READ (1st read): " + productRepository.findById(id).get());
        sleep(5000);
        System.out.println("REPEATABLE_READ (2nd read): " + productRepository.findById(id).get());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializable(Long id) {
        System.out.println("SERIALIZABLE: " + productRepository.findById(id).get());
        sleep(5000);
    }

    @Transactional
    public void updatePrice(Long id, double price) {
        Product p = productRepository.findById(id).orElseThrow();
        p.setPrice(price);
        productRepository.save(p);
        sleep(7000); // Keep txn open longer
        System.out.println("Updated price to: " + price);
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
