package com.example.sqch08ex2.controllers;

import com.example.sqch08ex2.model.Product;
import com.example.sqch08ex2.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        var products = productService.findAll();
        model.addAttribute("products", products);
        return "products.html";
    }

    @PostMapping("/products")
    public String addProduct(
            @RequestParam String name,
            @RequestParam double price,
            Model model
    ) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        productService.addProduct(product);

        var products = productService.findAll();
        model.addAttribute("products", products);
        return "products.html";
    }
}
