package com.ecommerce.product.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.infra.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Transactional
    public Product save(Product order) {
        return repository.save(order);
    }

    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }
}
