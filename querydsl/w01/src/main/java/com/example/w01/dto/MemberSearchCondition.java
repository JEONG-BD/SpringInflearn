package com.example.w01.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {
    private String membername;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;

}
