package com.example.sqch08ex2;

import com.example.sqch08ex2.controllers.ProductController;
import com.example.sqch08ex2.model.Product;
import com.example.sqch08ex2.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MainTestsv2 {

    @Mock
    private Model model;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void productGetSucceed() {

        Product p = new Product();
        p.setName("beer");
        p.setPrice(15);
        List<Product> list = new ArrayList<>();
        list.add(p);
        given(productService.findAll()).willReturn(list);

        String result = productController.viewProducts(model);
        assertEquals("products.html", result);
        verify(model).addAttribute("products", list);
    }

    @Test
    public void productPostSucceed() {

        Product p = new Product();
        p.setName("beer");
        p.setPrice(15);
        List<Product> list = new ArrayList<>();
        list.add(p);
        given(productService.findAll()).willReturn(list);

        String result = productController.addProduct(p, model);
        assertEquals("products.html", result);
        verify(model).addAttribute("products", list);
    }
}
