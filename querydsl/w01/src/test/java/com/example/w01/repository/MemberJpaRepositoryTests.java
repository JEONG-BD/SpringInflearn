package com.example.w01.repository;

import com.example.w01.entity.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTests {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() throws Exception{
        //given
         Member member = new Member("member1", 10);
         memberJpaRepository.save(member);
        //when

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        //then
        List<Member> result = memberJpaRepository.findAll();
        assertThat(findMember).isEqualTo(member);

        List<Member> result2 = memberJpaRepository.findByMembername("member1");
        assertThat(result2).containsExactly(member);

    }

    @Test
    public void basicQueryDslTest() throws Exception{
        //given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);
        //when

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        //then
        List<Member> result = memberJpaRepository.findAll_Querydsl();
        assertThat(findMember).isEqualTo(member);

        List<Member> result2 = memberJpaRepository.findByMembername_Querydsl("member1");
        assertThat(result2).containsExactly(member);

    }
}