package com.transactionexample.controller;

import com.transactionexample.isolationservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/read/uncommitted/{id}")
    public void readUncommitted(@PathVariable Long id) {
        productService.readUncommitted(id);
    }

    @GetMapping("/read/committed/{id}")
    public void readCommitted(@PathVariable Long id) {
        productService.readCommitted(id);
    }

    @GetMapping("/read/repeatable/{id}")
    public void repeatableRead(@PathVariable Long id) {
        productService.repeatableRead(id);
    }

    @GetMapping("/read/serializable/{id}")
    public void serializable(@PathVariable Long id) {
        productService.serializable(id);
    }

        @PostMapping("/update/{id}")
    public void updatePrice(@PathVariable Long id, @RequestParam double price) {
        productService.updatePrice(id, price);
    }
}
