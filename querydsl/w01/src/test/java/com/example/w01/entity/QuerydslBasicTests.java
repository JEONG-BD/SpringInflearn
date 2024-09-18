package com.example.w01.entity;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.w01.entity.QMember.member;
import static com.example.w01.entity.QTeam.team;
// import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.*;
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
    
    @Test
    public void aggregationTest() throws Exception{
        //given
        List<Tuple> result = queryFactory.select(member.count(),
                member.age.sum(),
                member.age.avg(),
                member.age.max(),
                member.age.min()).from(member).fetch();

        Tuple tuple = result.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);

    }

    @Test
    public void groupByTest() throws Exception{
        //given
        List<Tuple> result = queryFactory.select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();
        //when
        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);

        //then
    }
    
    @Test
    public void join1Test() throws Exception{
        //given
        List<Member> result = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        //when

        
        //then
        //assertThat(result).extracting("membername").ex("member1", "member2");
        assertThat(result).extracting("membername").containsExactly("member1", "member2");
    }
    
    @Test
    public void thetaJoinTest() throws Exception{
        //given
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        //when 
        List<Member> result = queryFactory.select(member)
                .from(member, team)
                .where(member.membername.eq(team.name)).fetch();
        //then
        assertThat(result).extracting("membername").containsExactly("teamA", "teamB");

    }
    
    @Test
    public void joinOnTest() throws Exception{
        //given
        List<Tuple> result = queryFactory.select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                .fetch();

        //when 
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
        //then
    }

    @Test
    public void joinOnNoRelation() throws Exception{
        //given

        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        //when

        List<Tuple> result = queryFactory.select(member, team)
                .from(member)
                .leftJoin(team).on(member.membername.eq(team.name))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple : " + tuple );
        }
        //then
    }

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchJoinNoTest() throws Exception{
        //given
        em.flush();
        em.clear();

        Member member1 = queryFactory
                .selectFrom(member)
                .where(member.membername.eq("member1")).fetchOne();
        //when

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(member1.getTeam());
        assertThat(loaded).as("패치 조인 미적용").isFalse();

        //then
    }

    @Test
    public void fetchJoinUseTest() throws Exception{
        //given
        em.flush();
        em.clear();

        Member member1 = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.membername.eq("member1")).fetchOne();
        //when

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(member1.getTeam());
        assertThat(loaded).as("패치 조인 미적용").isTrue();

        //then
    }
    
    @Test
    public void subQueryEqTest() throws Exception{
        //given

        QMember memberSub = new QMember("memberSub");
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member
                        .age.eq(JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub)))
                .fetch();
        //when
        assertThat(result).extracting("age").containsExactly(40);
        
        //then
    }

    @Test
    public void subQueryGoeTest() throws Exception{
        //given

        QMember memberSub = new QMember("memberSub");
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member
                        .age.goe(JPAExpressions
                                .select(memberSub.age.avg())
                                .from(memberSub)))
                .fetch();
        //when
        assertThat(result).extracting("age").containsExactly(30,40);

        //then
    }

    @Test
    public void subQueryInTest() throws Exception{
        //given

        QMember memberSub = new QMember("memberSub");
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member
                        .age.in(JPAExpressions
                                .select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(10))))
                .fetch();
        //when
        assertThat(result).extracting("age").containsExactly(20, 30,40);

        //then
    }

    @Test
    public void selectSubQueryTest() throws Exception{
        //given
        QMember memberSub = new QMember("memberSub");

        List<Tuple> fetch = queryFactory.select(member.membername,
                        JPAExpressions.select(memberSub.age.avg())
                                .from(memberSub))
                .from(member)
                .fetch();
        //when
        for (Tuple tuple : fetch) {
            System.out.println("tuple = " + tuple);
        }

        //then
    }

    @Test
    public void basicCaseTest() throws Exception{
        //given
        List<String> result = queryFactory.select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        for (String s : result) {
            System.out.println("s = "  + s);
        }
        //when

        //then
    }

    @Test
    public void complexCaseTest() throws Exception{
        //given
        List<String> result = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20 살")
                        .when(member.age.between(20, 30)).then("20~30살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        for (String s : result) {
            System.out.println("s = "  + s);
        }
        //when

        //then
    }
    
    
    @Test
    public void constTest() throws Exception{
        //given

        List<Tuple>  result = queryFactory.select(member.membername, Expressions.constant("A"))
                .from(member)
                .fetch();

        //when
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple );
        }
        
        //then
    }
    
    @Test
    public void concatTest() throws Exception{
        //given
        List<String> result = queryFactory.select(member.membername.concat("_").concat(member.age.stringValue()))
                .from(member)
                .fetch();
        //when 
        for (String s : result) {
            System.out.println("s = " + s);
        }

        //then
    }
    
    @Test
    public void simpleProjectionTest() throws Exception{
        //given
        List<String> result = queryFactory
                .select(member.membername)
                .from(member)
                .fetch();
        //when 
        for (String s : result) {
            System.out.println("s = " + s);
        }
        //then
    }
    
    @Test
    public void tupleProjectionTest() throws Exception{
        //given
        List<Tuple> tupleResult = queryFactory
                .select(member.membername, member.age)
                .from(member)
                .fetch();
        //when 
        for (Tuple tuple : tupleResult) {
            String memberName = tuple.get(member.membername);
            Integer memberAge = tuple.get(member.age);
            System.out.println("memberName = " + memberName);
            System.out.println("member Age = " + memberAge);
            System.out.println("=====");
        }

        //then
    }


}


