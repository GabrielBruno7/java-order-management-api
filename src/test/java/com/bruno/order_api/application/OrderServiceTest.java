package com.bruno.order_api.application;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.bruno.order_api.domain.money.Money;
import com.bruno.order_api.domain.order.Item;
import com.bruno.order_api.domain.order.Order;
import com.bruno.order_api.domain.order.OrderRepository;
import com.bruno.order_api.domain.product.Product;
import com.bruno.order_api.infrastructure.repository.InMemoryOrderRepository;

class OrderServiceTest {

    @Test
    void shouldCreateOrder() {
        Product product = new Product(
            java.util.UUID.randomUUID(),
            "Hambúrguer",
            new Money(new BigDecimal("25.90"))
        );

        Item item = new Item(product, 2);

        OrderRepository repository = new InMemoryOrderRepository();

        OrderService service = new OrderService(repository);

        Order order = service.createOrder(List.of(item));

        Order foundOrder = service.findOrderById(order.getId());

        assertEquals(order, foundOrder);

        assertNotNull(order);
        assertNotNull(order.getId());
        assertEquals(1, order.getItems().size());
        assertEquals(order, repository.findById(order.getId()));
    }
}
