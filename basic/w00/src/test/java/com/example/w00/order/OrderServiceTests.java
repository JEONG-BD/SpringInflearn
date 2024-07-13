package com.example.w00.order;

import com.example.w00.member.Grade;
import com.example.w00.member.Member;
import com.example.w00.member.MemberService;
import com.example.w00.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTests {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberID = 1L;
        Member member = new Member(memberID, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
