package com.example.w01.repository;

import com.example.w01.domain.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void add(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findById(Long id){
        return em.find(Item.class, id);
    }
    
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}