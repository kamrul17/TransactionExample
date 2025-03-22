package com.transactionexample.isolationservice;

import com.transactionexample.entity.Product;
import com.transactionexample.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
public static    int c=0;
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

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
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
//@Scheduled(fixedRate = 2000)
    public void ScheduleTesting() {

        System.out.println("ScheduleTesting : "+c++);
    sleep(5000);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProducts() {
       return productRepository.findAll();
    }

    public List<Product> getProductsWithQBE(int pageNo, int pageSize, Product product) {

//        Example<Product>productExample=Example.of(product);
//    Pageable pageable = PageRequest.of(pageNo, pageSize);
//         Page<Product> all = productRepository.findAll(productExample,pageable);
//         List<Product> content = all.getContent();
//return content;
         ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                 .withIgnorePaths("price")
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product>productExample=Example.of(product,exampleMatcher);
         Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> all = productRepository.findAll(productExample,pageable);
         List<Product> content = all.getContent();
        return content;
    }
}
