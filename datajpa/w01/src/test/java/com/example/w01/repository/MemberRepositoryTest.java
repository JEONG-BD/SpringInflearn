package com.example.w01.repository;

import com.example.w01.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
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
}