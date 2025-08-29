package com.ecommerce.order.application.representation.response;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse (
    Long id,
    String status,
    List<OrderItemResponse> items,
    LocalDateTime createdAt
) {}
