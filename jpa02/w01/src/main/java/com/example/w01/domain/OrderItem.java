package com.example.w01.domain;

import com.example.w01.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "PRICE")
    private int orderPrice;

    @Column(name = "COUNT")
    private int orderCount;


    public static OrderItem createOrderItem(Item item, int orderPrice, int orderCount){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setOrderCount(orderCount);

        item.removeStock(orderCount);
        return  orderItem;
    }
    public void cancel() {
        getItem().addStock(orderCount);
    }

    public int getOrderTotalPrice() {
        return getOrderPrice() * getOrderCount();
    }
}
