package com.bruno.order_api.domain.order;

import java.util.UUID;

public interface OrderRepository {

    void save(Order order);

    Order findById(UUID id);
}
