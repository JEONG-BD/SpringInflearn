package com.example.w01.repository;

import com.example.w01.dto.MemberDto;
import com.example.w01.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findBymemberNameAndAgeGreaterThan(String memberName, int age);

    //@Query(name = "Member.findByMemberName")
    List<Member> findByMemberName(@Param("memberName") String memberName);

    @Query("select m from Member m where m.memberName = :memberName and m.age = :age ")
    List<Member> findMember(@Param("memberName") String memberName, @Param("age") int age);

    @Query("select m.memberName from Member m ")
    List<String> memberNameList();


    @Query("select new com.example.w01.dto.MemberDto(m.id, m.memberName, t.teamName) from Member m join m.team t")
    List<MemberDto> findMemberDto();


    @Query("select m from Member m where m.memberName in :memberNames")
    List<Member> findByNamesList(@Param("memberNames") List<String> memberNames );

    // Multi row
    List<Member> findListByMemberName(String memberName);

    // One Row
    Member findMemberByMemberName(String memberName);

    // One Row
    Optional<Member> findOptionalMemberByMemberName(String memberName);

    @Query(value = "select m from Member m ", countQuery = "select count(m.memberName) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

}
