package com.example.w01.repository;

import com.example.w01.dto.MemberSearchCondition;
import com.example.w01.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> searchByWhere(MemberSearchCondition condition);
}
