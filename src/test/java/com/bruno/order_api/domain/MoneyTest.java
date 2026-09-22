package com.bruno.order_api.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {

    @Test
    void shouldCreateMoney() {
        BigDecimal value = new BigDecimal("25.90");

        Money money = new Money(value);

        assertEquals(value, money.getValue());
    }
}
