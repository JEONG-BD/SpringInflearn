package com.example.w01.domain.item;

import com.example.w01.domain.Category;
import com.example.w01.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "ITEM_PRICE")
    private int price;

    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;

    @ManyToMany(mappedBy = "itemList")
    private List<Category> categoryList = new ArrayList<>();

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;

        if(restStock == 0){
            throw new NotEnoughStockException("need More Stock");
        }

        this.stockQuantity -= quantity;
    }
}
