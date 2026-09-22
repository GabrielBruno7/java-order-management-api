package com.bruno.order_api.domain;

import java.util.UUID;

public class Product {

    private final UUID id;
    private final String name;
    private final Money price;

    public Product(UUID id, String name, Money price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}