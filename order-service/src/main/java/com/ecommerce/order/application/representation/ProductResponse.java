package com.ecommerce.order.application.representation;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private String description;
}
