package com.bruno.order_api.domain.order;

import com.bruno.order_api.domain.product.Product;

public class Item {

    private final Product product;
    private int quantity;

    public Item(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void increaseQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.quantity += quantity;
    }
}
