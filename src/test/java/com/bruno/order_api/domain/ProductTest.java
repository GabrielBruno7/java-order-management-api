package com.bruno.order_api.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

import com.bruno.order_api.domain.money.Money;
import com.bruno.order_api.domain.product.Product;

class ProductTest {

    @Test
    void shouldCreateProduct() {
        Money price = new Money(new BigDecimal("25.90"));
        String productName = "Hambúrguer";

        Product product = new Product(
            UUID.randomUUID(),
            productName,
            price
        );

        assertEquals(productName, product.getName());
        assertEquals(price, product.getPrice());
    }

    @Test
    void shouldNotAllowEmptyName() {
        Money price = new Money(new BigDecimal("25.90"));

        assertThrows(IllegalArgumentException.class, () -> new Product(UUID.randomUUID(), "", price));
    }

    @Test
    void shouldReturnProductId() {
        UUID id = UUID.randomUUID();

        Product product = new Product(
            id,
            "Hambúrguer",
            new Money(new BigDecimal("25.90"))
        );

        assertEquals(id, product.getId());
    }
}