package com.example.w00.scan;

import com.example.w00.AutoAppConfig;
import com.example.w00.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTests {

    @Test
    void basicScan(){
       AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

       MemberService memberService = ac.getBean(MemberService.class);
       assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
