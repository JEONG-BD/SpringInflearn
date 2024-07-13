package com.example.w00.order;

import com.example.w00.AppConfig;
import com.example.w00.discount.FixDiscountPolicy;
import com.example.w00.member.*;

public class OrderApp {
    public static void main(String[] args) {

        //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        //OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Order items = orderService.createOrder(memberId, "Item1", 10000);
        System.out.println(items);
        System.out.println(items.calculatePrice());
    }
}
