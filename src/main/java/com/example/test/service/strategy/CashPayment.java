package com.example.test.service.strategy;

class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(int orderId, double amount) {
        System.out.println("[CashPayment] Received cash for Order " + orderId + " amount: " + amount);
        return true;
    }
}