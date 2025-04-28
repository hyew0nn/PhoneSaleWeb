package com.mycom.myapp.product.repository;

import com.mycom.myapp.product.dto.ProductDto;
import com.mycom.myapp.product.entity.Product;
import com.mycom.myapp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("""
            select new com.mycom.myapp.product.dto.ProductDto(
                    p.id, p.name, b.brandName, p.color, p.price, p.releaseDate, p.storage, p.specs, p.stock
            )
            from Product p join p.brand b
            """)
    List<ProductDto> getProducts();
}
