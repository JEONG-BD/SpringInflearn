package com.example.w01.service;

import com.example.w01.domain.Member;
import com.example.w01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
//
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Transactional(readOnly = false)
    public Long joinMember(Member member){
        validateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void validateMember(Member member) {
        List<Member> findMember = memberRepository.findByMemberName(member.getName());
        if (!findMember.isEmpty()){
            throw new IllegalStateException("Error");
        }
    }

    public List<Member> allMember(){
        return memberRepository.findAll();
    }

    public Member findMember(Long memberId){
        return memberRepository.findByMember(memberId);
    }
}
