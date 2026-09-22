package com.bruno.order_api.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.bruno.order_api.application.OrderService;
import com.bruno.order_api.domain.money.Money;
import com.bruno.order_api.domain.order.Item;
import com.bruno.order_api.domain.product.Product;

import com.bruno.order_api.domain.order.Order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Test
    void shouldReturn404WhenOrderDoesNotExist() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get("/orders/" + id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Order not found"));
    }

    @Test
    void shouldReturnOrderWhenOrderExists() throws Exception {
        Product product = new Product(
                UUID.randomUUID(),
                "Hambúrguer",
                new Money(new BigDecimal("25.90"))
        );

        Item item = new Item(product, 2);

        Order order = orderService.createOrder(java.util.List.of(item));

        mockMvc.perform(get("/orders/" + order.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId().toString()))
                .andExpect(jsonPath("$.items[0].quantity").value(2))
                .andExpect(jsonPath("$.items[0].product.name").value("Hambúrguer"));
    }
}
