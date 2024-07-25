package com.example.w01.repository;

import com.example.w01.domain.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long orderId){
//        Order order = em.find(Order.class, orderId);
//        return order;
        return em.find(Order.class, orderId);
    }

//    public List<Order> findAll(OrderSearch orderSearch){
//        em.createQuery("select m from Order m", Order.class);
//    }
}
