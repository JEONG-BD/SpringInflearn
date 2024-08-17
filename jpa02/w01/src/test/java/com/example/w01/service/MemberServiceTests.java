package com.example.w01.service;
import com.example.w01.domain.Member;
import com.example.w01.repository.MemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    public void 회원가입 () throws Exception{
        //given
        Member member = new Member();
        member.setName("사용자A");

        //when
        Long memberId = memberService.add(member);


        //Member findMember = memberService.findOne(memberId);
        //System.out.println(findMember.getClass());

        //then
        //assert.AassertEquals(member, memberRepository.findByMember(memberId));
        //assertEquals(member, memberRepository.findById(memberId));
        Assert.assertEquals(member, memberRepository.findById(memberId));
        //assertEquals(member, findMember);
        //Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
        //Assertions.assertThat(member).isEqualTo(memberRepository.findById(memberId));

    }
}
