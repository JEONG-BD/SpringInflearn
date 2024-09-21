package com.example.w01.repository;

import com.example.w01.dto.MemberSearchCondition;
import com.example.w01.dto.MemberTeamDto;
import com.example.w01.dto.QMemberTeamDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

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

    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {

        List<MemberTeamDto> content = queryFactory.select(new QMemberTeamDto(member.id.as("memberId"),
                        member.membername, member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(memberNameEq(condition.getMembername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .offset((pageable.getOffset()))
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .where(memberNameEq(condition.getMembername()), teamNameEq(condition.getTeamName()), ageGoe(condition.getAgeGoe()), ageLoe(condition.getAgeLoe())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        return null;
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
