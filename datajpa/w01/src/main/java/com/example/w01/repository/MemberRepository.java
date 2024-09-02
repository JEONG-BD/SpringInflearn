package com.example.w01.repository;

import com.example.w01.dto.MemberDto;
import com.example.w01.entity.Member;
import jakarta.persistence.Lob;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
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

    @Query("select m from Member m left join fetch m.team" )
    List<Member> findMemberFetchJoin();


    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByMemberName(@Param("memberName") String memberName);


    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByMemberName(String memberName);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByMemberName(String memberName);
}
