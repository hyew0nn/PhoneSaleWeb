package com.mycom.myapp.repository;

import com.mycom.myapp.dto.ProductDto;
import com.mycom.myapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("""
            select new com.mycom.myapp.dto.ProductDto(
                    p.id, p.name, b.brandName, p.color, p.price, p.releaseDate, p.storage, p.specs, p.stock
            )
            from Product p join p.brand b
            """)
    List<ProductDto> getProducts();

    @Query("""
            select new com.mycom.myapp.dto.ProductDto(
                    p.id, p.name, b.brandName, p.color, p.price, p.releaseDate, p.storage, p.specs, p.stock
            )
            from Product p join p.brand b
            where p.id = :productId
            """)
    ProductDto getProductById(int productId);
}
