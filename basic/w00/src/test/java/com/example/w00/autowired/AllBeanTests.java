package com.example.w00.autowired;

import com.example.w00.AutoAppConfig;
import com.example.w00.discount.DiscountPolicy;
import com.example.w00.member.Grade;
import com.example.w00.member.Member;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTests {
    @Test
    void findAllBean(){
       AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

       DiscountService discountService = ac.getBean(DiscountService.class);
       Member member = new Member(1L, "memberA", Grade.VIP);
       int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService{

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policy Map = " + policyMap);
            System.out.println("policy list = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
