package com.example.test.service;

import com.example.test.dto.Cart;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private Map<Integer, Cart> userCarts = new HashMap<>();

    public Cart getCart(int userId) {
        return userCarts.computeIfAbsent(userId, Cart::new);
    }

    public void addToCart(int userId, int productId, int qty) {
        Cart cart = getCart(userId);
        cart.addItem(productId, qty);
        System.out.println("Added product " + productId + " qty " + qty + " to cart for user " + userId);
        System.out.println("cart" + cart);
        System.out.println("\n");

    }
}