package com.ecommerce.order.application;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.order.application.representation.ProductResponse;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductServiceClient {
    
    @GetMapping("/api/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
