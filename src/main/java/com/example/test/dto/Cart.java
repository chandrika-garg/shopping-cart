package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cart {
    private int userId;
    private List<CartItem> itemList;

    public Cart(int userId) {
        this.userId = userId;
        this.itemList = new ArrayList<>();
    }

    public void addItem(int productId, int qty) {
        itemList.add(new CartItem(productId, qty));
    }
}
