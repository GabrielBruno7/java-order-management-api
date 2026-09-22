package com.bruno.order_api.domain.money;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal value;

    public Money(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }

        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    public Money multiply(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        return new Money(
            this.value.multiply(BigDecimal.valueOf(quantity))
        );
    }
}