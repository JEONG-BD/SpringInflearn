package com.example.w01.service;

import com.example.w01.domain.Member;
import com.example.w01.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTests {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em ;

    @Test
    @Rollback(value = false)
    public void memberJoinTest() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when 
        Long savedId = memberService.join(member);

        em.flush();
        //then
        Assert.assertEquals(member, memberRepository.findOne(savedId));
    }
    
    @Test
    public void memberCheckTest() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("jeong");
        Member member2 = new Member();
        member2.setName("jeong");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }
        //then
        Assertions.fail("예외가 발생!!!!");
    }
}