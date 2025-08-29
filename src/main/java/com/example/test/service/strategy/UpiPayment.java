package com.example.test.service.strategy;

public class UpiPayment implements PaymentStrategy {
    @Override
    public boolean pay(int orderId, double amount) {
        System.out.println("[UpiPayment] UPI payment successful for Order " + orderId + " amount: " + amount);
        return true;
    }
}