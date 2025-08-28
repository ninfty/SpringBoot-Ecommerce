package com.ecommerce.order.domain.service;

import org.springframework.stereotype.Service;

import com.ecommerce.order.domain.entity.Order;

@Service
public class OrderDomainService {
    public void markAsCreated(Order order) {
        order.setStatus("CREATED");
    }
}
