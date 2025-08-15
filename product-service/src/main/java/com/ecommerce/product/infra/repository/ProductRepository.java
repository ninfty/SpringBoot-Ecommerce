package com.ecommerce.product.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.product.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
