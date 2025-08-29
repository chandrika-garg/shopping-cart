package com.example.test.service;

import com.example.test.dto.Payment;
import com.example.test.enums.PaymentMode;
import com.example.test.enums.PaymentStatus;
import com.example.test.service.strategy.PaymentStrategy;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    private Map<Integer, Payment> payments = new HashMap<>();
    private int paymentCounter = 1;

    public Payment processPayment(int orderId, double amount, PaymentMode mode, PaymentStrategy strategy) {
        boolean success = strategy.pay(orderId, amount);
        Payment payment = new Payment(paymentCounter++, orderId, amount, mode,
                success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
        payments.put(payment.getId(), payment);
        return payment;
    }
}