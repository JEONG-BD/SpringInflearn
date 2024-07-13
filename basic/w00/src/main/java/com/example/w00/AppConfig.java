package com.example.w00;

import com.example.w00.discount.FixDiscountPolicy;
import com.example.w00.discount.RateDiscountPolicy;
import com.example.w00.member.MemberService;
import com.example.w00.member.MemberServiceImpl;
import com.example.w00.member.MemoryMemberRepository;
import com.example.w00.order.OrderService;
import com.example.w00.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
