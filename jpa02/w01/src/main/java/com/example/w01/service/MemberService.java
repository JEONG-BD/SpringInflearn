package com.example.w01.service;

import com.example.w01.domain.Member;
import com.example.w01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long add(Member member){
        validate(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void validate(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if (!findMember.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원");
        }
    }

    public List<Member> all(){
        return memberRepository.findAll();
    }

//    public Member findOne(Long memberId){
//        return memberRepository.findById(memberId);
//    }


    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }

//    @Transactional
//    public void update(Long id, String name) {
//        Member findMember = memberRepository.findOne(id).get();
//        findMember.setName(name);
//    }

    @Transactional
    public void update(Long id, String name) {
        Member findMember = memberRepository.findById(id).get();
        findMember.setName(name);
    }
}
