package com.example.test.dto;

import com.example.test.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {
    int id;
    int userId;
    OrderStatus status;
    List<OrderItem> items;
    double totalAmount;

    public Order(int id, int userId, List<OrderItem> orderItems, double total) {
        this.id = id;
        this.userId = userId;
        this.items = orderItems;
        this.totalAmount = total;
        this.status = OrderStatus.CREATED;
    }
}
