package com.mycom.myapp.controller;

import com.mycom.myapp.dto.GetProductsResponse;
import com.mycom.myapp.dto.ProductDto;
import com.mycom.myapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable("id") int productId){
        ProductDto productDto = productService.findProductById(productId);
        return ResponseEntity.status(200).body(productDto);
    }
}
