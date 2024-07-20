package com.example.w01;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class MemberSample {

    @Id
    @GeneratedValue
    private Long id;
    private String username;

}
