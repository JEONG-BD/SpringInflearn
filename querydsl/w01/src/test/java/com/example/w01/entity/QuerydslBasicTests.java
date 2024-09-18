package com.example.w01.entity;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.w01.entity.QMember.member;

@SpringBootTest
@Transactional
public class QuerydslBasicTests {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before(){
        //given
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

    }

    @Test
    public void startJPQL() throws Exception{
        //given
        String qlString =
                "select m from Member m " +
                "where m.membername = :membername";

        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("membername", "member1")
                .getSingleResult();

        //when

        Assertions.assertThat(findMember.getMembername()).isEqualTo("member1");
        //then
    }


    @Test
    public void startQuerydsl() throws Exception{
        //given
        //JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        //QMember m = new QMember("m");
        QMember m = member;
        Member findMember = queryFactory.select(m)
                .from(m)
                .where(m.membername.eq("member1"))
                .fetchOne();
        //when

        Assertions.assertThat(findMember.getMembername()).isEqualTo("member1");
        //then
    }

    @Test
    public void qTypeTest() throws Exception{

        Member findMember = queryFactory.select(member)
                .from(member)
                .where(member.membername.eq("member1"))
                .fetchOne();
        //when

        Assertions.assertThat(findMember.getMembername()).isEqualTo("member1");
        //then
    }
    @Test
    public void searchTest() throws Exception{
        //given
        Member member1 = queryFactory
                .selectFrom(member)
                .where(member.membername.eq("member1")
                        .and(member.age.eq(10)))
                .fetchOne();
        //when

        //then
        Assertions.assertThat(member1.getMembername()).isEqualTo("member1");
    }

    @Test
    public void searchTest2() throws Exception{
        //given
        Member member1 = queryFactory
                .selectFrom(member)
                .where(
                        member.membername.eq("member1"),
                        member.age.eq(10))
                .fetchOne();
        //when

        //then
        Assertions.assertThat(member1.getMembername()).isEqualTo("member1");
    }

    @Test
    public void returnFetchTest() throws Exception{
        //given
        List<Member> fetchAll = queryFactory.selectFrom(member).fetch();
        Member fetchOne = queryFactory.selectFrom(member).fetchOne();
        Member fetchFirst = queryFactory.selectFrom(member).fetchFirst();

        QueryResults<Member> memberQueryResults = queryFactory.selectFrom(member).fetchResults();

        memberQueryResults.getTotal();
        List<Member> results = memberQueryResults.getResults();

    }

}
