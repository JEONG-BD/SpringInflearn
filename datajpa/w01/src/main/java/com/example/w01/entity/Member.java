package com.example.w01.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "memberName", "age"})
@NamedQuery(
        name = "Member.findByMemberName",
        query = "select m from Member m where m.memberName = :memberName"

)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String memberName;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
//    protected Member() {
//    }

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public Member(String memberName, int age) {
        this.memberName = memberName;
        this.age = age;
    }

    public Member(String memberName, int age, Team team) {
        this.memberName = memberName;
        this.age = age;
        changeTeam(team);
        //this.team = team;
    }

    public void changeMemberName(String memberName){
        this.memberName = memberName;
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
