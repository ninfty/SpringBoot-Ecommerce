package com.ecommerce.order.application.dto;

import com.ecommerce.order.domain.entity.Order;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderDTO {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "The quantity must be at least 1")
    private int quantity;

    public Order toEntity() {
        Order order = new Order();
        order.setProductId(this.productId);
        order.setQuantity(this.quantity);
        return order;
    }
}
