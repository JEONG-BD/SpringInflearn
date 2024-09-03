package com.example.w01.controller;

import com.example.w01.entity.Member;
import com.example.w01.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;


    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return member.getMemberName();
    }


    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member){
        return member.getMemberName();
    }

    @PostConstruct
    public void init(){
        memberRepository.save(new Member("ddd"));
    }
}