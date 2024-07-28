package com.example.w01.service;

import com.example.w01.domain.item.Book;
import com.example.w01.domain.item.Item;
import com.example.w01.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){

        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, Book book){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(book.getName());
        findItem.setPrice(book.getPrice());
        findItem.setStockQuantity(book.getStockQuantity());

        //itemRepository.save(findItem);
    }

    @Transactional
    public void updateItem2(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        //itemRepository.save(findItem);
    }


    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
