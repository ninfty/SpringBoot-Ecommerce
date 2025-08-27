package com.ecommerce.order.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.infra.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository repository;

    public List<Order> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));
    }

    @Transactional
    public Order save(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        return repository.save(order);
    }

    public Optional<Order> getById(Long id) {
        return repository.findById(id);
    }
}
