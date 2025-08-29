package com.ecommerce.order.application.representation.response;

public record OrderItemResponse(
    Long id,
    Long productId,
    Integer quantity,
    Double price
) {}
