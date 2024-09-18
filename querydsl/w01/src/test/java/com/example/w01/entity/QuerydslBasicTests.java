package com.example.w01.entity;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.w01.entity.QMember.member;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Commit
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
    

    @Test
    public void sortTest() throws Exception{
        //given
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));
        //when
        List<Member> result = queryFactory.selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.membername.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);
        assertThat(member5.getMembername()).isEqualTo("member5");
        assertThat(member6.getMembername()).isEqualTo("member6");
        assertThat(memberNull.getMembername()).isNull();
        //then

    }
    
    
    @Test
    public void paging1Test() throws Exception{
        //given
        List<Member> result = queryFactory.selectFrom(member)
                .orderBy(member.membername.desc())
                .offset(0)
                .limit(2)
                .fetch();
        //when 
        assertThat(result.size()).isEqualTo(2);
        //then
    }


    @Test
    public void paging2Test() throws Exception{
        //given
        List<Member> result = queryFactory.selectFrom(member)
                .orderBy(member.membername.desc())
                .offset(0)
                .limit(2)
                .fetch();        //when
        Long totalCount = queryFactory.select(member.count()).from(member).fetchOne();

        //when
        assertThat(totalCount).isEqualTo((4L));
    }
}
