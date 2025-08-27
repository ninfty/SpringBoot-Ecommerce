package com.ecommerce.order.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
