package com.example.w01.repository;

import com.example.w01.dto.MemberSearchCondition;
import com.example.w01.dto.MemberTeamDto;
import com.example.w01.entity.Member;
import com.example.w01.entity.QMember;
import com.example.w01.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class MemberRepositoryTests {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void basicTest() throws Exception{
        //given
        Member member = new Member("member1", 10);
        memberRepository.save(member);
        //when

        Member findMember = memberRepository.findById(member.getId()).get();
        //then
        List<Member> result = memberRepository.findAll();
        assertThat(findMember).isEqualTo(member);

        List<Member> result2 = memberRepository.findByMembername("member1");
        assertThat(result2).containsExactly(member);

    }


    @Test
    public void searchTest() throws Exception{
        //given
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


        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("TeamB");

        List<MemberTeamDto> memberTeamDtos = memberRepository.searchByWhere(condition);


        for (MemberTeamDto memberTeamDto : memberTeamDtos) {
            System.out.println("==========");
            System.out.println("memberTeamDto = " + memberTeamDto);
            System.out.println("==========");

        }
        assertThat(memberTeamDtos).extracting("membername").containsExactly("member4");


        //when

        //then
    }

    @Test
    public void searchPage() throws Exception{
        //given
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


        MemberSearchCondition condition = new MemberSearchCondition();
//        condition.setAgeGoe(35);
//        condition.setAgeLoe(40);
//        condition.setTeamName("TeamB");
//
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<MemberTeamDto> result = memberRepository.searchPageSimple(condition, pageRequest);

        assertThat(result.getSize()).isEqualTo(3);
        assertThat(result.getContent()).extracting("membername").containsExactly("member1", "member2", "member3");



        //when

        //then
    }
    
    @Test
    public void querydslPredicatedExecutorTest() throws Exception{


        //given
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

        QMember member = QMember.member;
        Iterable<Member> memberResult = memberRepository.findAll(member.age.between(20, 40).and(member.membername.like("member")));
        //when 

        for (Member row: memberResult) {
            System.out.println("member ==" + row);
        }

        //then
    }

}