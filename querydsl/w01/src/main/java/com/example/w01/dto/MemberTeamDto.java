package com.example.w01.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {

    private Long memberId;
    private String membername;
    private int age;
    private Long teamId;
    private String teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String membername, int age, Long teamId, String teamName) {
        this.memberId = memberId;
        this.membername = membername;
        this.age = age;
        this.teamId = teamId;
        this.teamName = teamName;
    }


}
