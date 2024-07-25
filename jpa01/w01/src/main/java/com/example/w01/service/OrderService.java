package com.example.w01.service;

import com.example.w01.domain.Delivery;
import com.example.w01.domain.Member;
import com.example.w01.domain.Order;
import com.example.w01.domain.OrderItem;
import com.example.w01.domain.item.Item;
import com.example.w01.repository.ItemRepository;
import com.example.w01.repository.MemberRepository;
import com.example.w01.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    public Long order(Long memberId, Long itemId, int count){

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    public void cancel(Long orderId){

    }
}
