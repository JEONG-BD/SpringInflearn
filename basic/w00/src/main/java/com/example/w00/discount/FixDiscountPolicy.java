package com.example.w00.discount;

import com.example.w00.member.Grade;
import com.example.w00.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP){
            return price - discountFixAmount;
        }else {
            return 0;
        }
    }
}
