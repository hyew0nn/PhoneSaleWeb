package com.mycom.myapp.repository;

import com.mycom.myapp.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    
}
