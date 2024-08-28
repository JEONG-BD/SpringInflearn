package com.example.w01.repository;

import com.example.w01.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTests {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void saveMember() {
        Member member = new Member("member A");
        Member saveMember =memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(saveMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getMemberName()).isEqualTo(member.getMemberName());
    }
}