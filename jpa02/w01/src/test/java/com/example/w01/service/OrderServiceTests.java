package com.example.w01.service;

import com.example.w01.domain.Address;
import com.example.w01.domain.Member;
import com.example.w01.domain.Order;
import com.example.w01.domain.OrderStatus;
import com.example.w01.domain.item.Book;
import com.example.w01.domain.item.Item;
import com.example.w01.exception.NotEnoughStockException;
import com.example.w01.repository.ItemRepository;
import com.example.w01.repository.MemberRepository;
import com.example.w01.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.junit.Assert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    EntityManager em;


    @Test
    @Rollback(value = false)
    public void 주문생성() throws Exception{
        //given
        Member member = getMember();

        Item item = getItem();

        //when
        int count = 5;
        Long orderId = orderService.createOrder(member.getId(), item.getId(), count);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("Order Status ", OrderStatus.ORDER, getOrder.getStatus() );
        Assert.assertEquals("Order Item count", 1, getOrder.getOrderItemList().size());
        Assert.assertEquals("Order Price", 100*5, getOrder.getTotalPrice());
        Assert.assertEquals("Stock Quantity", 5, item.getStockQuantity());
    }


    @Test
    public void 주문취소() throws Exception{
        Member member = getMember();

        Item item = getItem();
        int count = 5;
        Long orderId = orderService.createOrder(member.getId(), item.getId(), count);

        //when
        orderService.orderCancel(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assert.assertEquals("Order Cancel Status", OrderStatus.CANCEL, getOrder.getStatus());
        Assert.assertEquals("Item Stock", 10, item.getStockQuantity());
    }
    
    @Test
    public void 재고확인() throws Exception{
        //given
        Member member = getMember();
        Item item = getItem();

        int orderCount = 11;

        //when
        orderService.createOrder(member.getId(), item.getId(), orderCount);

        //then
        fail("재고 부족");

    }


    private Item getItem() {
        Item item = new Book();
        item.setItemName("Book Name");
        item.setStockQuantity(10);
        item.setPrice(100);
        em.persist(item);
        return item;
    }

    private Member getMember() {
        Member member = new Member();
        member.setAddress(new Address("seoul", "seoul-ro-1", "123-456"));
        member.setName("사용자1");
        em.persist(member);
        return member;
    }
}