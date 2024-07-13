package com.example.w00.order;

import com.example.w00.AppConfig;
import com.example.w00.discount.FixDiscountPolicy;
import com.example.w00.discount.RateDiscountPolicy;
import com.example.w00.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTests {

    //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
    //OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder(){
        Long memberID = 1L;
        Member member = new Member(memberID, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
