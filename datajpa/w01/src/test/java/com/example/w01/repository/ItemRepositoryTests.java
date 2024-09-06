package com.example.w01.repository;

import com.example.w01.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTests {

    @Autowired ItemRepository itemRepository;

    @Test
    public void save() throws Exception{
        Item item = new Item("tets");
        itemRepository.save(item);

    }
}