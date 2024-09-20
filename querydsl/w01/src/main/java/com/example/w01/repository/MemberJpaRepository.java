package com.example.w01.repository;

import com.example.w01.entity.Member;
import com.example.w01.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.w01.entity.QMember.*;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberJpaRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member){
        em.persist(member);
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m ", Member.class).getResultList();
    }



    public List<Member> findAll_Querydsl(){
        return queryFactory.selectFrom(member).fetch();

    }

    public List<Member> findByMembername(String membername){
        return em.createQuery("select m from Member m where m.membername = :membername", Member.class).setParameter("membername", membername).getResultList();
    }

    public List<Member> findByMembername_Querydsl(String membername){
        return queryFactory.selectFrom(member).where(member.membername.eq(membername)).fetch();
    }



}
