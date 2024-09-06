package com.example.w01.dto;

import com.example.w01.entity.Member;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String memberName;
    private String teamName;

    public MemberDto(Long id, String memberName, String teamName) {
        this.id = id;
        this.memberName = memberName;
        this.teamName = teamName;
    }

    public MemberDto(Member member){
        this.id = member.getId();
        this.memberName = member.getMemberName();
    }
}
