package com.bruno.order_api.domain;

import org.junit.jupiter.api.Test;

import com.bruno.order_api.domain.order.Item;
import com.bruno.order_api.domain.order.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bruno.order_api.domain.money.Money;
import com.bruno.order_api.domain.product.Product;

class OrderTest {

    @Test
    void shouldCreateOrder() {
        Money price = new Money(new BigDecimal("25.90"));
        Product product = new Product(UUID.randomUUID(), "Hambúrguer", price);
        Item item = new Item(product, 2);

        UUID id = UUID.randomUUID();

        Order order = new Order(id, List.of(item));

        assertEquals(id, order.getId());
        assertEquals(1, order.getItems().size());
    }

    @Test
    void shouldCalculateOrderTotal() {
        Money hamburgerPrice = new Money(new BigDecimal("25.90"));
        Product hamburger = new Product(UUID.randomUUID(), "Hambúrguer", hamburgerPrice);
        Item hamburgerItem = new Item(hamburger, 2);

        Money sodaPrice = new Money(new BigDecimal("6.00"));
        Product soda = new Product(UUID.randomUUID(), "Refrigerante", sodaPrice);
        Item sodaItem = new Item(soda, 1);

        Order order = new Order(
            UUID.randomUUID(),
            List.of(hamburgerItem, sodaItem)
        );

        assertEquals(
            new BigDecimal("57.80"),
            order.getTotal().getValue()
        );
    }

    @Test
    void shouldNotCreateOrderWithoutItems() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Order(
                UUID.randomUUID(),
                List.of()
            )
        );
    }

    @Test
    void shouldNotAllowNullItem() {
        Product product = new Product(
            UUID.randomUUID(),
            "Hambúrguer",
            new Money(new BigDecimal("25.90"))
        );

        Item item = new Item(product, 1);

        Order order = new Order(
            UUID.randomUUID(),
            List.of(item)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> order.addItem(null)
        );
    }

    @Test
    void shouldIncreaseQuantityWhenAddingSameProduct() {
        UUID productId = UUID.randomUUID();

        Product hamburger = new Product(
            productId,
            "Hambúrguer",
            new Money(new BigDecimal("25.90"))
        );

        Item firstItem = new Item(hamburger, 2);
        Item secondItem = new Item(hamburger, 1);

        Order order = new Order(
            UUID.randomUUID(),
            List.of(firstItem)
        );

        order.addItem(secondItem);

        assertEquals(1, order.getItems().size());
        assertEquals(3, order.getItems().get(0).getQuantity());
    }

    @Test
    void shouldAddSeparateItemsWhenProductsAreDifferent() {
        Product hamburger = new Product(
            UUID.randomUUID(),
            "Hambúrguer",
            new Money(new BigDecimal("25.90"))
        );

        Product soda = new Product(
            UUID.randomUUID(),
            "Refrigerante",
            new Money(new BigDecimal("6.00"))
        );

        Order order = new Order(
            UUID.randomUUID(),
            List.of(new Item(hamburger, 1))
        );

        order.addItem(new Item(soda, 1));

        assertEquals(2, order.getItems().size());
    }
}
