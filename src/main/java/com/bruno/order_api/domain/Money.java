package com.bruno.order_api.domain;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    public Money multiply(int quantity) {
        return new Money(this.value.multiply(BigDecimal.valueOf(quantity)));
    }
}