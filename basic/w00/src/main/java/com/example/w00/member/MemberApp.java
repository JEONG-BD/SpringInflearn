package com.example.w00.member;

public class MemberApp {
    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);

        System.out.println(memberA.getName());
        System.out.println(findMember.getName());
        System.out.println("===");

    }
}
