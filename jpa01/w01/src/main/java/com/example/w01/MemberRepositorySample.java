package com.example.w01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositorySample {

    @PersistenceContext
    private EntityManager em;

    public Long save(MemberSample member){
        em.persist(member);
        return member.getId();
    }

    public MemberSample find(Long id){
        return em.find(MemberSample.class, id);
    }
}
