package com.example.w01.service;

import com.example.w01.domain.Delivery;
import com.example.w01.domain.Member;
import com.example.w01.domain.Order;
import com.example.w01.domain.OrderItem;
import com.example.w01.domain.item.Item;
import com.example.w01.repository.ItemRepository;
import com.example.w01.repository.MemberRepository;
import com.example.w01.repository.OrderRepository;
import com.example.w01.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    @Transactional(readOnly = false)
    public Long createOrder(Long memberId, Long itemId, int count){
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findById(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.orderCreate(order);
        return order.getId();
    }

    @Transactional(readOnly = false)
    public void orderCancel(Long id){
        Order order = orderRepository.findOne(id);
        order.cancelOrder();

    }

    public List<Order> orderSearch(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }


}
