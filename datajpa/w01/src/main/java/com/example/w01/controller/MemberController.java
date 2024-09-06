package com.example.w01.controller;

import com.example.w01.dto.MemberDto;
import com.example.w01.entity.Member;
import com.example.w01.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/members")
    public Page<Member> listv1(@PageableDefault(size=5)  Pageable pageable){
        Page<Member> page = memberRepository.findAll(pageable);
        return page;
    }


    @GetMapping("/members/v2")
    public Page<MemberDto> listv2(@PageableDefault(size=5)  Pageable pageable){
        Page<Member> page = memberRepository.findAll(pageable);
        Page<MemberDto> map = page.map(member -> new MemberDto(member.getId(), member.getMemberName(), null));
        return map;
    }
//    @PostConstruct
//    public void init(){
//        for (int i=0; i< 100; i++){
//            memberRepository.save(new Member("ddd"+i, 10));
//        }
//    }
}