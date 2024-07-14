package com.example.w00.singleton;

import com.example.w00.AppConfig;
import com.example.w00.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTests {

    @Test
    @DisplayName("pure container")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 : " +memberService1 );
        System.out.println("memberService1 : " +memberService2 );


        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

}
