package com.example.w01.domain;

import com.example.w01.domain.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    private Item item;


    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;
    private int count;


}
