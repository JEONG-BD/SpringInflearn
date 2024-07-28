package com.example.w01.service;

import com.example.w01.domain.item.Book;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTests {

    @Autowired
    EntityManager em;


    @Test
    public void updateTest() throws Exception{
        //given
        Book book = em.find(Book.class, 1L);
        book.setName("new book");


        //when

        //then
    }
}
