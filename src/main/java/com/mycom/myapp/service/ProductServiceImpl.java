package com.mycom.myapp.service;

import com.mycom.myapp.dto.GetProductsResponse;
import com.mycom.myapp.dto.ProductDto;
import com.mycom.myapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public GetProductsResponse findProductAll() {
        GetProductsResponse response = new GetProductsResponse();

        List<ProductDto> products = productRepository.getProducts();
        response.setProducts(products);
        response.setMessage("success");

        return response;
    }

    @Override
    public ProductDto findProductById(int productId) {
        return productRepository.getProductById(productId);
    }
}
