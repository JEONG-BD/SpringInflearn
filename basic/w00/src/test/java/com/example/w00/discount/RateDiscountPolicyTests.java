package com.example.w00.discount;

import com.example.w00.member.Grade;
import com.example.w00.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTests {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();


    @Test
    @DisplayName("VIP 10% discount")
    void vip_o(){
        Member member = new Member(1L, "memberO", Grade.VIP);

        int discount = rateDiscountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("No discount")
    void vip_x(){
        Member member = new Member(1L, "memberO", Grade.BASIC);

        int discount = rateDiscountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(0);
    }
}
