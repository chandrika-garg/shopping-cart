package com.example.test.service.strategy;

class CardPayment implements PaymentStrategy {
    @Override
    public boolean pay(int orderId, double amount) {
        System.out.println("[CardPayment] Charged card for Order " + orderId + " amount: " + amount);
        return true;
    }
}