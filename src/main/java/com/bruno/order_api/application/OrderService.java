package com.bruno.order_api.application;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String getOrders() {
        return "Orders endpoint";
    }
}
