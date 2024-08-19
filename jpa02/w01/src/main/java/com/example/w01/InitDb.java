package com.example.w01;

import com.example.w01.domain.*;
import com.example.w01.domain.item.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        //initService.dbInit1();
        //initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;


        public void dbInit1() {
            Member member = createMember("이순신","Busan", "Busan-ro 1", "123-456");
            em.persist(member);

            Book book1 = creaateBook("JPA1", 10000, 100);
            Book book2 = creaateBook("JPA2", 10000, 100);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 2);

            Delivery delivery = createDelivery(member);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            em.persist(order);
        }

        private Book creaateBook(String bookName, int bookPrice, int stockQuantity) {
            Book book = new Book();
            book.setItemName(bookName);
            book.setPrice(bookPrice);
            book.setStockQuantity(stockQuantity);
            return book;
        }


        public void dbInit2() {
            Member member = createMember("김철수", "Seoul", "Seoul-ro 1", "123-456");
            em.persist(member);

            Book book1 = creaateBook("Spring 1", 20000, 200);
            Book book2 = creaateBook("Spring 2", 40000, 300);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }


        private Member createMember(String name, String city, String street, String zipCode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipCode));
            return member;
        }
    }

    private static Member createMember(String name, String city, String street, String zipCode) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address(city, street, zipCode));
        return member;
    }

}


