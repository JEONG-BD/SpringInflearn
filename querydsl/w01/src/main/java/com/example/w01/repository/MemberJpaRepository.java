package com.example.w01.repository;

import com.example.w01.dto.MemberSearchCondition;
import com.example.w01.dto.MemberTeamDto;
import com.example.w01.dto.QMemberTeamDto;
import com.example.w01.entity.Member;
import com.example.w01.entity.QMember;
import com.example.w01.entity.QTeam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.example.w01.entity.QMember.*;
import static com.example.w01.entity.QTeam.*;
import static org.springframework.util.StringUtils.*;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberJpaRepository(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        //this.queryFactory = new JPAQueryFactory(em);
        this.queryFactory = queryFactory;
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

    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition){
        BooleanBuilder builder = new BooleanBuilder();

        if (hasText(condition.getMembername())){
            builder.and(member.membername.eq(condition.getMembername()));
        }
        if (hasText(condition.getTeamName())){
            builder.and(team.name.eq(condition.getTeamName()));
        }
        if (condition.getAgeGoe() != null){
            builder.and(member.age.goe(condition.getAgeGoe()));
        }
        if (condition.getAgeLoe() != null){
            builder.and(member.age.loe(condition.getAgeLoe()));
        }


        return queryFactory.select(new QMemberTeamDto(member.id.as("memberId"),
                        member.membername, member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .where(builder)
                .leftJoin(member.team, team)
                .fetch();

    }

    public List<MemberTeamDto> searchByWhere(MemberSearchCondition condition) {

        return queryFactory.select(new QMemberTeamDto(member.id.as("memberId"),
                        member.membername, member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .where(memberNameEq(condition.getMembername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .leftJoin(member.team, team)
                .fetch();


    }

    private BooleanExpression memberNameEq(String memberName) {
        return hasText(memberName) ? member.membername.eq(memberName) : null ;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null ;
    }
    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null ;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null ;
    }



}
