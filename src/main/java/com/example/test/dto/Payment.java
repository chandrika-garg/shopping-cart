package com.example.test.dto;

import com.example.test.enums.PaymentMode;
import com.example.test.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Payment {
    int id;
    int orderId;
    double amount;
    PaymentMode mode;
    PaymentStatus status;
}