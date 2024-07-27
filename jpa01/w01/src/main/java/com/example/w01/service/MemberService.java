package com.example.w01.service;

import com.example.w01.domain.Member;
import com.example.w01.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    @Transactional
    public Long join(Member member){
        validDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    //@Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.finaAll();
    }

    //@Transactional(readOnly = true)
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
