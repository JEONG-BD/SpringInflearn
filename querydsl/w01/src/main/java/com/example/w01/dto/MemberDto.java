package com.example.w01.dto;

import lombok.Data;

@Data
public class MemberDto {

    private String membername;
    private int age;

    public MemberDto() {
    }

    public MemberDto(String membername, int age) {
        this.membername = membername;
        this.age = age;
    }
}
