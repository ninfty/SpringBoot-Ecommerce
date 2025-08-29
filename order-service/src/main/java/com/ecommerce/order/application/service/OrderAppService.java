package com.ecommerce.order.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.order.application.representation.response.OrderItemResponse;
import com.ecommerce.order.application.representation.response.OrderResponse;
import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.domain.entity.OrderItem;
import com.ecommerce.order.domain.service.OrderDomainService;
import com.ecommerce.order.infra.client.ProductServiceClient;
import com.ecommerce.order.infra.repository.OrderRepository;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderAppService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final OrderDomainService orderDomainService;

    public List<Order> getAll() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Transactional
    public OrderResponse save(Order order) {
        try {
            for (OrderItem item : order.getItems()) {
                productServiceClient.getProductById(item.getProductId());
            }

            orderDomainService.markAsCreated(order);

            Order saved = orderRepository.save(order);

            return new OrderResponse(
                    saved.getId(),
                    saved.getStatus(),
                    saved.getItems().stream()
                            .map(item -> new OrderItemResponse(
                                    item.getId(),
                                    item.getProductId(),
                                    item.getQuantity(),
                                    item.getPrice()))
                            .toList(),
                    saved.getCreatedAt());
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "One or more products not found");
        }
    }

    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }
}
