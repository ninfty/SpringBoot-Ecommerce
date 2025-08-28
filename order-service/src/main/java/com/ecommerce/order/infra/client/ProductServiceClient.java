package com.ecommerce.order.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.order.application.dto.ProductResponse;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductServiceClient {
    
    @GetMapping("/api/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
