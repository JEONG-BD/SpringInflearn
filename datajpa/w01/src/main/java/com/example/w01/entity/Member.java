package com.example.w01.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "memberName", "age"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String memberName;

    private int age;

    @ManyToOne()
    @JoinColumn(name = "TEAM_ID")
    private Team team;
//    protected Member() {
//    }

    public Member(String memberName) {
        this.memberName = memberName;
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
