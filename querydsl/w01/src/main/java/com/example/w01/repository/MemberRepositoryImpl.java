package com.example.w01.repository;

import com.example.w01.dto.MemberSearchCondition;
import com.example.w01.dto.MemberTeamDto;
import com.example.w01.dto.QMemberTeamDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.w01.entity.QMember.member;
import static com.example.w01.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override

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
