package com.ecommerce.order.application.representation.request;

import java.util.List;

import com.ecommerce.order.domain.entity.Order;
import com.ecommerce.order.domain.entity.OrderItem;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderDTO {
    @NotEmpty(message = "Item list is required")
    private List<OrderItemDTO> items;

    public Order toEntity() {
        Order order = new Order();
        
        for (OrderItemDTO itemDto : items) {
            OrderItem item = new OrderItem();

            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            order.addItem(item);
        }

        return order;
    }
}
