package com.bruno.order_api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final List<Item> items;

    public Order(UUID id, List<Item> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        this.id = id;
        this.items = new ArrayList<>(items);
    }

    public UUID getId() {
        return id;
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }

    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        Item existingItem = findItemByProductId(item.getProduct().getId());

        if (existingItem != null) {
            existingItem.increaseQuantity(item.getQuantity());
            return;
        }

        items.add(item);
    }

    public Money getTotal() {
        Money total = new Money(BigDecimal.ZERO);

        for (Item item : items) {
            Money itemTotal = item.getProduct()
                    .getPrice()
                    .multiply(item.getQuantity());

            total = total.add(itemTotal);
        }

        return total;
    }

    private Item findItemByProductId(UUID productId) {
        for (Item item : items) {
            if (item.getProduct().getId().equals(productId)) {
                return item;
            }
        }

        return null;
    }
}