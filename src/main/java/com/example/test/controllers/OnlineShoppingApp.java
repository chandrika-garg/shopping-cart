package com.example.test.controllers;

import com.example.test.dto.*;
import com.example.test.enums.*;
import com.example.test.service.*;
import com.example.test.service.strategy.*;

import java.util.*;

public class OnlineShoppingApp {
    public static void main(String[] args) {
        // Setup sample products
        Map<Integer, Product> products = new HashMap<>();
        products.put(1, new Product(1, "iPhone", "Smartphone", 1000, 10, 1));
        products.put(2, new Product(2, "Nike Shoes", "Running shoes", 150, 20, 2));

        // Services
        CartService cartService = new CartService();
        OrderService orderService = new OrderService(products);
        PaymentService paymentService = new PaymentService();

        // User
        User user = new User(1, "Alice", "alice@example.com", "123456");

        // Step 1: Add to Cart
        cartService.addToCart(user.getId(), 1, 1); // 1 iPhone
        cartService.addToCart(user.getId(), 2, 2); // 2 Shoes

        // Step 2: Create Order from Cart
        Cart cart = cartService.getCart(user.getId());
        Order order = orderService.createOrder(user.getId(), cart);

        System.out.println("order created" + order);
        System.out.println("\n");

        // Step 3: Make Payment (UPI Strategy)
        PaymentStrategy upiPayment = new UpiPayment();
        Payment payment = paymentService.processPayment(order.getId(), order.getTotalAmount(), PaymentMode.UPI, upiPayment);

        // Step 4: Confirm Order
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            orderService.confirmOrder(order, upiPayment);
        }

        // Final State
        System.out.println("Final Order Status: " + order.getStatus());
    }
}