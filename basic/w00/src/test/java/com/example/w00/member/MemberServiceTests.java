package com.example.w00.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTests {

    MemberService memberService = new MemberServiceImpl();
    @Test
    void join(){
        // given
        Member member = new Member(1L, "memberTest", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
