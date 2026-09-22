package com.bruno.order_api.domain;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.bruno.order_api.domain.money.Money;

class MoneyTest {

    @Test
    void shouldCreateMoney() {
        BigDecimal value = new BigDecimal("25.90");

        Money money = new Money(value);

        assertEquals(value, money.getValue());
    }

    @Test
    void shouldNotAllowNegativeValue() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Money(new BigDecimal("-10.00"))
        );
    }

    @Test
    void shouldAddMoneyValues() {
        Money first = new Money(new BigDecimal("10.50"));
        Money second = new Money(new BigDecimal("5.25"));

        Money result = first.add(second);

        assertEquals(
            new BigDecimal("15.75"),
            result.getValue()
        );
    }

    @Test
    void shouldMultiplyMoneyByQuantity() {
        Money price = new Money(new BigDecimal("25.90"));

        Money result = price.multiply(3);

        assertEquals(
            new BigDecimal("77.70"),
            result.getValue()
        );
    }

    @Test
    void shouldNotMultiplyByInvalidQuantity() {
        Money price = new Money(new BigDecimal("25.90"));

        assertThrows(
            IllegalArgumentException.class,
            () -> price.multiply(0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> price.multiply(-1)
        );
    }

    @Test
    void shouldNotChangeOriginalMoneyWhenAdding() {
        Money first = new Money(new BigDecimal("10.00"));
        Money second = new Money(new BigDecimal("5.00"));

        Money result = first.add(second);

        assertEquals(new BigDecimal("10.00"), first.getValue());
        assertEquals(new BigDecimal("15.00"), result.getValue());
    }
}
