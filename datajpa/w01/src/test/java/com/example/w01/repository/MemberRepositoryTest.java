package com.example.w01.repository;

import com.example.w01.dto.MemberDto;
import com.example.w01.entity.Member;
import com.example.w01.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired EntityManager em;

    @Test
    public void saveMember () throws Exception{
        Member member = new Member("member A");
        Member saveMember =memberRepository.save(member);

        Member findMember = memberRepository.findById(saveMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getMemberName()).isEqualTo(member.getMemberName());
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    public void countTest() throws Exception{
        //given

        //when
        List<Member> all = memberRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();

        assertThat(count).isEqualTo(2);
    }


    @Test
    public void deleteTest() throws Exception{
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        //when
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long count = memberRepository.count();

        //then
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void memberNameAndAgeTest() throws Exception{
        //given
        Member aaa = new Member("AAA", 10);
        Member bbb = new Member("AAA", 20);

        memberRepository.save(aaa);
        memberRepository.save(bbb);

        //when
        List<Member> memberList = memberRepository.findBymemberNameAndAgeGreaterThan("AAA", 10);

        for (Member member : memberList) {
            System.out.println(member.getMemberName());
        }
        //then
    }

    @Test
    public void namedQueryTest() throws Exception{
        //given
        Member aaa = new Member("TDD", 10);

        memberRepository.save(aaa);
        //when
        List<Member> namedQueryList = memberRepository.findByMemberName("TDD");

        for (Member member : namedQueryList) {
            System.out.println("=====");
            System.out.println(member);
        }
        //then
        assertThat(namedQueryList.get(0).getMemberName()).isEqualTo("TDD");

    }
    
    @Test
    public void repositoryQueryTest() throws Exception{
        //given
        Member memberA = new Member("DDD", 12);
        memberRepository.save(memberA);

        //when

        List<Member> findMemberList = memberRepository.findMember("DDD", 12);

        //then
        assertThat(findMemberList.get(0).getMemberName()).isEqualTo(memberA.getMemberName());

    }

    @Test
    public void nameListTest() throws Exception{
        //given
        List<String> members = memberRepository.memberNameList();
        //when
        for (String name : members) {
            System.out.println(name);
        }
        //then
    }
    @Test
    public void fidndMemberDto() throws Exception{
        //given
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);

        Member memberA = new Member("A0001", 10);
        memberA.setTeam(teamA);

        memberRepository.save(memberA);

        //when
        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println(dto.getMemberName());
            System.out.println(dto.getTeamName());

        }

        //then
    }

    @Test
    public void collectionTest() throws Exception{
        //given
        List<Member> byNamesList = memberRepository.findByNamesList(Arrays.asList("AAA", "A0001"));
        //when

        for (Member member : byNamesList) {
            System.out.println(member);
        }
        //then
    }

    @Test
    public void findListByMemberListTest() throws Exception{
        //given
        List<Member> memberList = memberRepository.findListByMemberName("BBB");
        //when
        System.out.println(memberList.size());
        for (Member member : memberList) {
            System.out.println(member);
        }
        //then
    }

    @Test
    public void findMemberByMemberNameTest() throws Exception{
        //given
        Member findMember = memberRepository.findMemberByMemberName("A0001");
        System.out.println(findMember);
        //then
    }

    @Test
    public void findOptionalMemberByMemberName() throws Exception{
        //given
        Optional<Member> optionalMember = memberRepository.findOptionalMemberByMemberName("CCC");
        //when
        System.out.println(optionalMember);

        //then
    }

    @Test
    public void pagingTest() throws Exception{
        //given
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 10));

        int age = 10;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "memberName"));

        int offset = 1;
        int limit = 3;

        //when
        Page<Member> page = memberRepository.findByAge(age,  pageRequest);
        //long count = memberRepository.totalCount(age);
        Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getMemberName(), null));


        //then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

        for (Member member : content) {
            System.out.println("member = " + member);

        }
        System.out.println("total element = " + totalElements );

        assertThat(content.size()).isEqualTo(3);
        //assertThat(members.getTotalElements()).isEqualTo(30);
        assertThat(page.getNumber()).isEqualTo(0);
        //assertThat(members.getTotalPages()).isEqualTo(17);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
        assertThat(page.isLast()).isFalse();

    }


    @Test
    public void bulkUpdate() throws Exception{
        //given
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 11));
        memberRepository.save(new Member("AAA", 11));
        memberRepository.save(new Member("AAA", 13));
        memberRepository.save(new Member("AAA", 13));
        memberRepository.save(new Member("AAA", 15));
        memberRepository.save(new Member("AAA", 15));
        memberRepository.save(new Member("AAA", 15));
        memberRepository.save(new Member("AAA", 16));
        memberRepository.save(new Member("AAA", 17));

        //when
        int updateCount = memberRepository.bulkAgePlus(15);
        //then
        assertThat(updateCount).isEqualTo(5);
    }

    @Test
    public void findMemberLazy() throws Exception{
        //given
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member memberA = new Member("member1", 10, teamA);
        Member memberB = new Member("member2", 10, teamB);

        memberRepository.save(memberA);
        memberRepository.save(memberB);

        em.flush();
        em.clear();

        //when
        //List<Member> members = memberRepository.findMemberFetchJoin();
        //List<Member> members = memberRepository.findAll();
        List<Member> members = memberRepository.findEntityGraphByMemberName("member1");
        for (Member member : members) {
            System.out.println("member = " + member.getMemberName());
            System.out.println("member.team = " + member.getTeam().getClass());
        }
        //then

    }

    @Test
    public void queryTest() throws Exception{
        //given
        Member member = new Member("member1", 10);
        memberRepository.save(member);

        em.flush();
        em.clear();
        //when

        Member findMember = memberRepository.findReadOnlyByMemberName("member2");
        findMember.setMemberName("member2");

        em.flush();
        //then

    }

    @Test
    public void lockTest() throws Exception{
        //given
        Member member = new Member("member1", 10);
        memberRepository.save(member);

        em.flush();
        em.clear();
        //when

        List<Member> members = memberRepository.findLockByMemberName("member2");

        //then
    }
    
    @Test
    public void callCustom() throws Exception{
        //given
        List<Member> memberCustom = memberRepository.findMemberCustom();
        //when 
        for (Member member : memberCustom) {
            System.out.println(member.getMemberName());
        }
        //then
    }

    @Test
    public void JpaEventBaseEntity() throws Exception{
        //given
        Member member = new Member("JJJJ", 10);
        memberRepository.save(member);

        Thread.sleep(100);
        member.setMemberName("TTTT");
        //when
        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(member.getId()).get();

        //then
        System.out.println("find Member " + findMember);
        System.out.println(findMember.getCreatedDate());
        System.out.println(findMember.getLastModifiedDate());
        System.out.println(findMember.getCreatedBy());
        System.out.println(findMember.getLastModifiedBy());

    }

    @Test
    public void queryByExample() throws Exception{
        //given
        Team team = new Team("AAA");
        em.persist(team);
        Member member1 = new Member("m1", 10, team);
        Member member2 = new Member("m2", 10, team);
        em.persist(member1);
        em.persist(member2);
        em.flush();
        em.clear();

        //memberRepository.findByMemberName(member1.getMemberName());

        //when
        Member member = new Member("m1", 10);
        Team team1 = new Team("AAA");
        member.setTeam(team1);
        ExampleMatcher age = ExampleMatcher.matching().withIgnorePaths("age");
        Example<Member> example = Example.of(member, age);
        List<Member> all = memberRepository.findAll(example);

        //then
        assertThat(all.get(0).getMemberName()).isEqualTo("m1");
    }

    @Test
    public void projects() throws Exception{
        //given
        Team team = new Team("AAA");
        em.persist(team);
        Member member1 = new Member("m1", 10, team);
        Member member2 = new Member("m2", 10, team);
        em.persist(member1);
        em.persist(member2);
        em.flush();
        em.clear();
        //when
        List<UserNameOnly> memberList = memberRepository.findProjectionByMemberName("m1");
        for (UserNameOnly userNameOnly : memberList) {
            System.out.println(userNameOnly);
        }

//        List<MemberNameOnlyDto> memberList = memberRepository.findProjectionByMemberName("m1");
//        for (MemberNameOnlyDto member: memberList) {
//            System.out.println(member.getMemberName());
//        }
        //then
    }
}