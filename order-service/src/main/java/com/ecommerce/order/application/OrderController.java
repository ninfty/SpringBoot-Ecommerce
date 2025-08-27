package com.ecommerce.order.application;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.order.application.representation.OrderDTO;
import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.infra.repository.OrderRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping
    public Order createOrder(@RequestBody @Valid OrderDTO dto) {
        Order order = dto.toEntity();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");
        
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> ResponseEntity.ok(order))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"));
    }
}
