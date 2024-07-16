package com.example.w00.discount;

import com.example.w00.annotation.MainDiscountPolicy;
import com.example.w00.member.Grade;
import com.example.w00.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@Primary
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100  ;
        } else {
            return 0;
        }
    }
}
