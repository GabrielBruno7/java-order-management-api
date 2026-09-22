package com.bruno.order_api.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

class ItemTest {
    @Test
    void shouldCreateItem() {
        Money price = new Money(new BigDecimal("25.90"));
        String productName = "Hambúrguer";

        Product product = new Product(UUID.randomUUID(), productName, price);

        Item item = new Item(product, 1);

        assertEquals(productName, item.getProduct().getName());
        assertEquals(1, item.getQuantity());
    }

    @Test
    void shouldNotAllowInvalidQuantity() {
        Money price = new Money(new BigDecimal("25.90"));
        Product product = new Product(UUID.randomUUID(), "Hambúrguer", price);

        assertThrows(
            IllegalArgumentException.class,
            () -> new Item(product, 0)
        );
    }

    @Test
    void shouldNotAllowNullProduct() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Item(null, 1)
        );
    }
}