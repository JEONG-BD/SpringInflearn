package com.example.w01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String memberName;

    protected Member() {
    }

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public void changeMemberName(String memberName){
        this.memberName = memberName;
    }
}
