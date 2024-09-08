package com.example.w01.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UserNameOnly {
    //@Value(("#{target.MemberName + '' + target.age}"))
    String getMemberName();


}
