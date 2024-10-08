package com.example.w01.repository;

import com.example.w01.domain.Order;
import com.example.w01.repository.order.simplequery.SimpleOrderQueryDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void orderCreate(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> finaAll(OrderSearch orderSearch){
        List<Order> resultList = em.createQuery("select o from Order o join o.member m" +
                        " where o.status = :orderStatus " +
                        "and m.name like :name", Order.class)
                .setParameter("orderStatus", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();

        return resultList;
    }

    public List<Order> findAllByString(OrderSearch orderSearch){
        /*return em.createQuery("select o from Order o join o.member m" +
                " where o.status =:status" +
                " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();*/

        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        if(orderSearch.getOrderStatus() != null){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        if(StringUtils.hasText(orderSearch.getMemberName())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        System.out.println(jpql);
        System.out.println(jpql);
        System.out.println(jpql);
        System.out.println(jpql);

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);

        if(orderSearch.getOrderStatus() != null){
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }

        if(StringUtils.hasText(orderSearch.getMemberName())){
            query = query.setParameter("name", orderSearch.getMemberName());
        }
        return query.getResultList();
        //return em.createQuery(jpql, Order.class).setMaxResults(1000).getResultList();
    }


    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();
        if(orderSearch.getOrderStatus() != null){
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        if(StringUtils.hasText(orderSearch.getMemberName())){

            Predicate name = cb.like(m.get("name"), "%"+ orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }

//
//    public List<SimpleOrderQueryDto> findOrderDtos(){
//        return em.createQuery("select new com.example.w01.repository.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o " +
//                " join o.member m" +
//                " join o.delivery d", SimpleOrderQueryDto.class).getResultList();
//    }

    public List<Order> findAllWithItem(){
        List<Order> resultList = em.createQuery("select distinct o from Order o " +
                "join fetch o.delivery d " +
                "join fetch o.member m " +
                "join fetch o.orderItemList oi " +
                "join fetch oi.item i", Order.class)
                .setFirstResult(1)
                .setMaxResults(100)
        .getResultList();

        return resultList;
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery("select o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d", Order.class).getResultList();
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {

        return em.createQuery("select o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}

