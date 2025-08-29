package com.ecommerce.order.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.order.application.representation.request.OrderDTO;
import com.ecommerce.order.application.representation.response.OrderResponse;
import com.ecommerce.order.application.service.OrderAppService;
import com.ecommerce.order.domain.entity.Order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderAppService orderAppService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderAppService.getAll();
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody @Valid OrderDTO dto) {
        Order order = dto.toEntity();

        return orderAppService.save(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderAppService.getById(id)
                .map(order -> ResponseEntity.ok(order))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"));
    }
}
