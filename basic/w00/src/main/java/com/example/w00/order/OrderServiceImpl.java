package com.example.w00.order;

import com.example.w00.discount.DiscountPolicy;
import com.example.w00.discount.FixDiscountPolicy;
import com.example.w00.discount.RateDiscountPolicy;
import com.example.w00.member.Member;
import com.example.w00.member.MemberRepository;
import com.example.w00.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //private DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
