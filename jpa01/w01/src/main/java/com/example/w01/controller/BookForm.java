package com.example.w01.controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;
    private int price;
    private String name;
    private int stockQuantity;
    private String author;
    private String isbn;

}
