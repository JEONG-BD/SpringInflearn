package com.example.w01.service;

import com.example.w01.domain.Address;
import com.example.w01.domain.Member;
import com.example.w01.domain.Order;
import com.example.w01.domain.OrderStatus;
import com.example.w01.domain.item.Book;
import com.example.w01.domain.item.Item;
import com.example.w01.exception.NotEnoughStockException;
import com.example.w01.repository.MemberRepository;
import com.example.w01.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTests {

    @Autowired
    EntityManager em;
    MemberRepository memberRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("상품주문")
    public void orderTest() throws Exception{
        //given

        Member member = createMember();
        Book book = createBook("JPA Book", 10000, 10);
        /*
        Book book = new Book();
        book.setName("Book JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        */

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야 한다",1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량", 10000 *orderCount, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량 만큼 재고가 줄어야 한다", 8, book.getStockQuantity());

    }

    @Test
    @DisplayName("주문취소")
    public void orderCancel() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook("JPA2", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assert.assertEquals("주문 취소시 상태는 Cancel", OrderStatus.CANCEL, getOrder.getStatus());
        Assert.assertEquals("주문 취소된 상품은 그만큼 재고가 증가해야 한다", 10, book.getStockQuantity());

    }

    @Test
    @DisplayName("재고수량초과")
    public void orderStock() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook("JPA2", 10000, 10);
        int orderCount = 11;

        //when
        try {
            orderService.order(member.getId(), book.getId(), orderCount);
        } catch (NotEnoughStockException ex) {
            return;
        }
        //then
        fail("재고 수량 예외가 발생해야 한다.");
    }

    private Member createMember(){
        Member member = new Member();
        member.setAddress(new Address("Seoul", "seoul ro", "123-122"));
        em.persist(member);
        return member;
    }

    private Book createBook(String bookName, int bookPrice, int bookStock){
        Book book = new Book();
        book.setName(bookName);
        book.setPrice(bookPrice);
        book.setStockQuantity(bookStock);
        em.persist(book);
        return book;
    }

}