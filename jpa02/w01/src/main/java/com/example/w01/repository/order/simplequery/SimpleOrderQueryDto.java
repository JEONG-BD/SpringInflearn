package com.example.w01.repository.order.simplequery;

import com.example.w01.domain.Address;
import com.example.w01.domain.Order;
import com.example.w01.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
//
//    public SimpleOrderQueryDto(Order order){
//        orderId = order.getId();
//        name = order.getMember().getName();
//        orderDate = order.getOrderDate();
//        orderStatus = order.getStatus();
//        address = order.getDelivery().getAddress();
//
//    }

    public SimpleOrderQueryDto(Long orderId,
                               String name,
                               LocalDateTime orderDate,
                               OrderStatus orderStatus,
                               Address address){

        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }


}
