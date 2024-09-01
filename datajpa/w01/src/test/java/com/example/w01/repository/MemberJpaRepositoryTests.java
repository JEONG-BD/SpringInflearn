package com.example.w01.repository;

import com.example.w01.entity.Member;
import org.junit.jupiter.api.Assertions;
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
class MemberJpaRepositoryTests {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void saveMember() {
        Member member = new Member("member A");
        Member saveMember =memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(saveMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getMemberName()).isEqualTo(member.getMemberName());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() throws Exception{
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        //when
        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();

        //then
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);
    }

    @Test
    public void countTest() throws Exception{
        //given

        //when
        List<Member> all = memberJpaRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);

        long count = memberJpaRepository.count();

        assertThat(count).isEqualTo(2);
    }


    @Test
    public void deleteTest() throws Exception{
        //given
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        //when
        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();

        //when
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);

        long count = memberJpaRepository.count();

        //then
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void memberNameAndAgeTest() throws Exception{
        //given
        Member aaa = new Member("AAA", 10);
        Member bbb = new Member("AAA", 20);

        memberJpaRepository.save(aaa);
        memberJpaRepository.save(bbb);

        //when
        List<Member> memberList = memberJpaRepository.findByMembernameAndAgeGreaterThen("AAA", 10);

        //then
        for (Member member : memberList) {
            System.out.println("=====");
            System.out.println(member);
        }
        assertThat(memberList.get(0).getMemberName()).isEqualTo("AAA");
        assertThat(memberList.get(0).getAge()).isEqualTo(20);

    }
    
    @Test
    public void namedQueryTest() throws Exception{
        //given
        Member aaa = new Member("AAA", 10);
        Member bbb = new Member("AAA", 20);

        memberJpaRepository.save(aaa);
        memberJpaRepository.save(bbb);
        //when
        List<Member> namedQueryList = memberJpaRepository.findByMemberName("AAA");

        for (Member member : namedQueryList) {
            System.out.println("=====");
            System.out.println(member);
        }
        //then
        assertThat(namedQueryList.get(0).getMemberName()).isEqualTo("AAA");

    }

    @Test
    public void pagingTest() throws Exception{
        //given
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 10));

        int age = 10;
        int offset = 1;
        int limit = 3;

        //when
        List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
        long count = memberJpaRepository.totalCount(age);

        //then
        assertThat(members.size()).isEqualTo(3);
        assertThat(count).isEqualTo(10);
    }
    
    @Test
    public void bulkUpdate() throws Exception{
        //given
        memberJpaRepository.save(new Member("AAA", 10));
        memberJpaRepository.save(new Member("AAA", 11));
        memberJpaRepository.save(new Member("AAA", 11));
        memberJpaRepository.save(new Member("AAA", 13));
        memberJpaRepository.save(new Member("AAA", 13));
        memberJpaRepository.save(new Member("AAA", 15));
        memberJpaRepository.save(new Member("AAA", 15));
        memberJpaRepository.save(new Member("AAA", 15));
        memberJpaRepository.save(new Member("AAA", 16));
        memberJpaRepository.save(new Member("AAA", 17));

        //when
        int updateCount = memberJpaRepository.bulkAgePlus(16);
        //then
        assertThat(updateCount).isEqualTo(2);
    }

}