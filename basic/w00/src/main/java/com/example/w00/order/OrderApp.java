package com.example.w00.order;

import com.example.w00.member.Grade;
import com.example.w00.member.Member;
import com.example.w00.member.MemberService;
import com.example.w00.member.MemberServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Order items = orderService.createOrder(memberId, "Item1", 10000);
        System.out.println(items);
        System.out.println(items.calculatePrice());
    }
}
