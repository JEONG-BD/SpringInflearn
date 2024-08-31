package com.example.w01.repository;

import com.example.w01.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;


    public Member save(Member member){
        em.persist(member);
        return member;
    }


    public Member find(Long id){
        return em.find(Member.class, id);
    }

    public void delete(Member member){
        em.remove(member);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findByMembernameAndAgeGreaterThen(String memberName, int age){
        return em.createQuery("select m from Member m" +
                " where m.memberName = : memberName " +
                " and m.age > :age")
                .setParameter("memberName", memberName)
                .setParameter("age", age)
                .getResultList();
    }

    public long count(){
        return em.createQuery("select count(m) from Member  m", Long.class).getSingleResult();
    }


}
