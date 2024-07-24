package com.example.w01.service;

import com.example.w01.domain.Member;
import com.example.w01.repository.MemberRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void memberJoinTest() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when 
        Long savedId = memberService.join(member);

        //then
        Assert.assertEquals(member, memberRepository.findOne(savedId));
    }
    
    @Test
    public void memberCheckTest() throws Exception{
        //given
         
        //when 
        
        //then
    }
}