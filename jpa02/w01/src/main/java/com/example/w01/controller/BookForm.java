package com.example.w01.controller;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class BookForm {

    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
    private Long id;
}
