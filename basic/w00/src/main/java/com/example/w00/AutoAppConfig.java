package com.example.w00;

import com.example.w00.discount.DiscountPolicy;
import com.example.w00.member.MemberRepository;
import com.example.w00.member.MemoryMemberRepository;
import com.example.w00.order.OrderService;
import com.example.w00.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.example.w00",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name="memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }

//    @Autowired MemberRepository memberRepository;
//    @Autowired DiscountPolicy discountPolicy;
//
//    @Bean
//    OrderService orderService(){
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }

//    @Bean
//    OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }


}
