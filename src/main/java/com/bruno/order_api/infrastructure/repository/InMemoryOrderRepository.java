package com.bruno.order_api.infrastructure.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bruno.order_api.domain.order.Order;
import com.bruno.order_api.domain.order.OrderRepository;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<UUID, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Order findById(UUID id) {
        return orders.get(id);
    }
}
