package com.example.w01.api;

import com.example.w01.domain.Address;
import com.example.w01.domain.Order;
import com.example.w01.domain.OrderItem;
import com.example.w01.domain.OrderStatus;
import com.example.w01.repository.OrderRepository;
import com.example.w01.repository.OrderSearch;
import com.example.w01.repository.order.query.OrderFlatDto;
import com.example.w01.repository.order.query.OrderItemQueryDto;
import com.example.w01.repository.order.query.OrderQueryDto;
import com.example.w01.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> orderV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());


        for(Order row : all){
            System.out.println("====");
            System.out.println(row);
            System.out.println("====");
            row.getMember().getName();
            row.getDelivery().getAddress();
            List<OrderItem> orderItems = row.getOrderItemList();
            orderItems.stream().forEach(o -> o.getItem().getItemName());
        }
        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> orderV2(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> collect = all.stream().map(o -> new OrderDto(o)).collect(Collectors.toList());

        return collect;
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> orderV3(){
        List<Order> all = orderRepository.findAllWithItem();
        for (Order order : all) {
            System.out.println("order ref = " + order + " id = "  + order.getId());
        }

        List<OrderDto> collect = all.stream().map(o -> new OrderDto(o)).collect(Collectors.toList());

        return collect;
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orderV3_page(@RequestParam(value = "offest", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "100") int limit){
        System.out.println("v3.1===================================");
        List<Order> all = orderRepository.findAllWithMemberDelivery(offset, limit);
//        for (Order order : all) {
//            System.out.println("order ref = " + order + " id = "  + order.getId());
//        }

        List<OrderDto> collect = all.stream().map(o -> new OrderDto(o)).collect(Collectors.toList());

        return collect;
    }


    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> orderV4(){
        System.out.println("v4===================================");
        return orderQueryRepository.findOrderQueryDtos();
    }


    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> orderV5(){
        System.out.println("v5===================================");
        return orderQueryRepository.findAllByDto_optimization();
    }


    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> orderV6(){
        System.out.println("v6===================================");
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();
//
//        flats.stream().collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(),o.getName(),
//                o.getOrderDate(),
//                o.getOrderStatus(),
//                o.getAddress()), Collectors.mapping(o -> new OrderItemQueryDto(o.getOrderId(),
//                o.getItemName(),
//                o.getOrderPrice(),
//                o.getCount()).toList()))).entrySet().stream().map(e -> new OrderQueryDto(e.getKey().getOrderId(),
//                e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(), e.getKey().getAddress(), e.getValue())).collect(Collectors.toList());

        return flats.stream()
                .collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(),
                                o.getName(),
                                o.getOrderDate(),
                                o.getOrderStatus(),
                                o.getAddress()), mapping(o -> new OrderItemQueryDto(
                                        o.getOrderId(),
                        o.getItemName(),
                        o.getOrderPrice(),
                        o.getCount()),
                        toList()))).entrySet().stream().map(e -> new OrderQueryDto(e.getKey().getOrderId(),
                        e.getKey().getName(),
                        e.getKey().getOrderDate(),
                        e.getKey().getOrderStatus(),
                        e.getKey().getAddress(),
                        e.getValue()))
                .collect(toList());
    }

    @Data
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;
        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
//            order.getOrderItemList().stream().forEach(o -> o.getItem().getItemName());
//            orderItems = order.getOrderItemList();
            orderItems = order.getOrderItemList().stream().map(orderItem -> new OrderItemDto(orderItem)).collect(Collectors.toList());
        }
    }

    @Data
    static class OrderItemDto {
        private String itemName;
        private int itemPrice;
        private int count;
        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getItemName();
            itemPrice = orderItem.getOrderPrice();
            count = orderItem.getOrderCount();
        }
    }
}
