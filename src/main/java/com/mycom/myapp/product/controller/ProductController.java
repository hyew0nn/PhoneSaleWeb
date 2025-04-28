package com.mycom.myapp.product.controller;

import com.mycom.myapp.product.dto.GetProductsResponse;
import com.mycom.myapp.product.dto.ProductDto;
import com.mycom.myapp.product.entity.Product;
import com.mycom.myapp.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

//     #0
//     Controller 에서 Entity 를 Json 변환, 응단
    @GetMapping
    public GetProductsResponse findProductAll() {
        return productService.findProductAll();
    }
}
