package com.example.w01.repository;

import com.example.w01.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){

        if (item.getId() == null){
           em.persist(item);
        } else {
           em.merge(item);
        }
    }

    public Item findOne(Long itemId){
//        Item item = em.find(Item.class, itemId);
//        return item;
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
