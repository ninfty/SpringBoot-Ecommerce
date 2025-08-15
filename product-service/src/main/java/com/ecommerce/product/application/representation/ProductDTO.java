package com.ecommerce.product.application.representation;

import com.ecommerce.product.domain.entity.Product;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price can't be zero")
    private double price;
    
    @Size(max = 255, message = "Description must be max 255 characters")
    private String description;

    public Product toEntity() {
        Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        product.setDescription(this.description);
        return product;
    }
}
