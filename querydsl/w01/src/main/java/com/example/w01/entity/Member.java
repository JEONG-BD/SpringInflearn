package com.example.w01.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.logging.Level;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "membername", "age"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String membername;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    public Member(String membername) {
        this(membername, 0);
    }

    public Member(String membername, int age) {
        this(membername, age, null);
    }

    public Member(String membername, int age, Team team) {
        this.membername = membername;
        this.age = age;
        if (team != null){
            changeTeam(team);
        }

    }

    private void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
