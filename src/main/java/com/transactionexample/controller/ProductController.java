package com.transactionexample.controller;

import com.transactionexample.entity.Product;
import com.transactionexample.isolationservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/read/uncommitted/{id}")
    public void readUncommitted(@PathVariable Long id) {
        productService.readUncommitted(id);
    }
//    @GetMapping("/ScheduleTesting")
//    public void ScheduleTesting() {
//        productService.ScheduleTesting();
//    }


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

        @PutMapping("/update/{id}")
    public void updatePrice(@PathVariable Long id, @RequestParam double price) {
        productService.updatePrice(id, price);
    }
    @PostMapping("/add")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
    @GetMapping("/all")
    public List<Product> getProducts() {
       return productService.getProducts();
    }
    @GetMapping("/allWithQBE")
    public List<Product> getProductsWithQBE(@RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue ="6" ) int pageSize,@RequestBody Product product) {
        return productService.getProductsWithQBE(pageNo,pageSize,product);
    }
}
