package com.example.w01.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {


    private String city;

    private String street;

    private String zipcode;
}
