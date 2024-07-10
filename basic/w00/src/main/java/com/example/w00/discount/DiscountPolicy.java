package com.example.w00.discount;

import com.example.w00.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
