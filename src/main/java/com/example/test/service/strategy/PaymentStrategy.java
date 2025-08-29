package com.example.test.service.strategy;

public interface PaymentStrategy {
    boolean pay(int orderId, double amount);
}