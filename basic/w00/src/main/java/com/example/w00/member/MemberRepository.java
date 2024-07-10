package com.example.w00.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
