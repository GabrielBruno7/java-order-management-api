package com.bruno.order_api.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bruno.order_api.domain.order.Order;
import com.bruno.order_api.domain.order.OrderNotFoundException;
import com.bruno.order_api.domain.order.Item;
import com.bruno.order_api.domain.order.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(List<Item> items) {
        UUID id = UUID.randomUUID();

        Order order = new Order(id, items);

        repository.save(order);

        return order;
    }

    public Order findOrderById(UUID id) {
        Order order = repository.findById(id);

        if (order == null) {
            throw new OrderNotFoundException("Order not found");
        }

        return order;
    }
}
