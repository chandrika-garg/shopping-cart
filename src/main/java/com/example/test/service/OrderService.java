package com.example.test.service;

import com.example.test.dto.*;
import com.example.test.enums.OrderStatus;
import com.example.test.service.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private Map<Integer, Product> products;
    private Map<Integer, Order> orders = new HashMap<>();
    private int orderCounter = 1;

    public OrderService(Map<Integer, Product> products) {
        this.products = products;
    }

    public Order createOrder(int userId, Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        // Stock check & deduction
        for (CartItem ci : cart.getItemList()) {
            Product p = products.get(ci.getProductId());
            if (p == null || p.getStockQuantity() < ci.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product " + ci.getQuantity());
            }
            p.setStockQuantity(p.getStockQuantity() - ci.getQuantity());
            orderItems.add(new OrderItem(p.getId(), ci.getQuantity(), p.getPrice()));
            total += p.getPrice() * ci.getQuantity();
        }

        Order order = new Order(orderCounter++, userId, orderItems, total);
        orders.put(order.getId(), order);
        System.out.println("Created order " + order.getId() + " for user " + userId + " total=" + total);
        return order;
    }

    public void confirmOrder(Order order, PaymentStrategy strategy) {
        boolean paid = strategy.pay(order.getId(), order.getTotalAmount());
        if (paid) {
            order.setStatus(OrderStatus.PAID);
            System.out.println("Order " + order.getId() + " confirmed and paid.");

        } else {
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println("Order " + order.getId() + " cancelled due to payment failure.");
            System.out.println("\n");

        }
    }

    public Order getOrder(int id) {
        return orders.get(id);
    }
}
