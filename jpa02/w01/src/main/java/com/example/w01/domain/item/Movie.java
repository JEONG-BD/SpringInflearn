package com.example.w01.domain.item;

import com.example.w01.domain.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String title;

}
