package com.ecommerce.order.infra.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ecommerce.order.domain.entity.Order;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldFindOrderById() {
        Order order = new Order();
        order.setStatus("CREATED");

        orderRepository.save(order);

        Optional<Order> found = orderRepository.findById(order.getId());

        assertTrue(found.isPresent());
    }
}
