package com.ecommerce.order.application.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderItemDTO {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "The quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Price is required")
    private double price;
}
