package com.example.w00.member;

public class MemberServiceImpl implements MemberService{

    //private static MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = new MemoryMemberRepository();
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
