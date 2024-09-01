package com.example.w01.repository;

import com.example.w01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findBymemberNameAndAgeGreaterThan(String memberName, int age);

    //@Query(name = "Member.findByMemberName")
    List<Member> findByMemberName(@Param("memberName") String memberName);

    @Query("select m from Member m where m.memberName = :memberName and m.age = :age ")
    List<Member> findMember(@Param("memberName") String memberName, @Param("age") int age);

}
