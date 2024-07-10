package com.example.w00.order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
