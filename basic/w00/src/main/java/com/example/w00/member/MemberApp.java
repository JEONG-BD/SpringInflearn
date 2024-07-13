package com.example.w00.member;

import com.example.w00.AppConfig;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);

        System.out.println(memberA.getName());
        System.out.println(findMember.getName());
        System.out.println("===");

    }
}
