package com.ecommerce.order.application.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.order.application.representation.response.ProductResponse;
import com.ecommerce.order.infra.client.ProductServiceClient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductServiceClient productClient;

    @Test
    void shouldCreateOrder() throws Exception {
        ProductResponse product = new ProductResponse(1L, "Notebook", 2500.0, "description");
        when(productClient.getProductById(1L)).thenReturn(product);

        String json = """
            {
              "items": [
                { "productId": 1, "quantity": 2 }
              ]
            }
        """;

        mockMvc.perform(post("/api/orders")
                .contentType("application/json")
                .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("CREATED"));
    }
}
