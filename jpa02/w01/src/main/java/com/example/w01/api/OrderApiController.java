package com.example.w01.api;

import com.example.w01.domain.Order;
import com.example.w01.domain.OrderItem;
import com.example.w01.repository.OrderRepository;
import com.example.w01.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> findAll(){
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
}
