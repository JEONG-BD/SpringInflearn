package com.example.w00.beanfind;

import com.example.w00.AppConfig;
import com.example.w00.member.Member;
import com.example.w00.member.MemberService;
import com.example.w00.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Bean name")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("Bean Type")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Bean Name2")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /*

    @Test
    @DisplayName("Bean NameX")
    void findBeanByNameX(){
        MemberService memberService = ac.getBean("xxxx", MemberServiceImpl.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx"), MemberService.class);
    }
    */
}
