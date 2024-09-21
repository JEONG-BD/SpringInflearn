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

}