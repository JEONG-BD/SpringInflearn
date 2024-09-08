package com.example.w01.repository;

public class MemberNameOnlyDto {

    private final String memberName;

    public MemberNameOnlyDto(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }
}
