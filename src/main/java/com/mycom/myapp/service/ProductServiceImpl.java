package com.mycom.myapp.service;

import com.mycom.myapp.dto.GetProductsResponse;
import com.mycom.myapp.dto.InsertOrderItemRequest;
import com.mycom.myapp.dto.ProductDto;
import com.mycom.myapp.entity.OrderItems;
import com.mycom.myapp.entity.Product;
import com.mycom.myapp.exception.ProductException;
import com.mycom.myapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public GetProductsResponse findProductAll() {
        GetProductsResponse response = new GetProductsResponse();

        List<ProductDto> products = productRepository.getProducts();
        response.setProducts(products);
        response.setCount(products.size());
        response.setMessage("success");

        return response;
    }

    @Override
    public ProductDto findProductById(int productId) {
        ProductDto productDto = productRepository.getProductById(productId);

        if (productDto == null) {
            throw new ProductException.ProductNotFoundException(productId);
        }

        return productDto;
    }

    @Override
    public Boolean decreaseProductStock(List<InsertOrderItemRequest> items) {
        for (InsertOrderItemRequest item : items) {
            Optional<Product> optionalProduct = productRepository.findById(item.getProductId());

            if (optionalProduct.isEmpty()) {
                throw new ProductException.ProductNotFoundException(item.getProductId());
            }

            Product product = optionalProduct.get();

            if (product.getStock() < item.getQuantity()) {
                throw new ProductException.ProductInsufficientException(product.getName());
            }

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }
        return true;
    }
}
