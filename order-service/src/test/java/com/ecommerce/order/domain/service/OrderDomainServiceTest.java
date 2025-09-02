package com.ecommerce.order.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.order.domain.entity.Order;

@ExtendWith(MockitoExtension.class)
public class OrderDomainServiceTest {
    @Test
    void shouldMarkOrderAsCreated() {
        Order order = new Order();
        OrderDomainService service = new OrderDomainService();  

        service.markAsCreated(order);

        assertEquals("CREATED", order.getStatus());
    }
}
