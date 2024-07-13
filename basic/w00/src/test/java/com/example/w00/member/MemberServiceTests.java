package com.example.w00.member;

import com.example.w00.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTests {

    //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
    MemberService memberService ;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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
