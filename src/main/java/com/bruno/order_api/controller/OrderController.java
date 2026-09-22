package com.bruno.order_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.order_api.application.OrderService;
import com.bruno.order_api.domain.order.Order;
import com.bruno.order_api.domain.order.Item;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody List<Item> items) {
        return orderService.createOrder(items);
    }

    @GetMapping("/orders/{id}")
    public Order findOrderById(@PathVariable UUID id) {
        return orderService.findOrderById(id);
    }
}
