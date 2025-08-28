package com.ecommerce.order.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.order.application.representation.ProductResponse;
import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.infra.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository repository;

    private final ProductServiceClient productServiceClient;

    public List<Order> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Transactional
    public Order save(Order order) {
        try {
            productServiceClient.getProductById(order.getProductId());

            order.setStatus("CREATED");

            return repository.save(order);

        } catch (feign.FeignException.NotFound e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not found with id: " + order.getProductId()
            );

        } catch (feign.FeignException e) {
            throw new ResponseStatusException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Product service is temporarily unavailable. Error: " + e.getMessage()
            );
            
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error occurred: " + e.getMessage()
            );
        }
    }

    public Optional<Order> getById(Long id) {
        return repository.findById(id);
    }
}
