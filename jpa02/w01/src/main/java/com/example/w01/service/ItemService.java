package com.example.w01.service;

import com.example.w01.domain.item.Book;
import com.example.w01.domain.item.Item;
import com.example.w01.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = false)
    public void add(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, Book book){
        Item findItem = itemRepository.findById(itemId);
        findItem.setItemName(book.getItemName());
        findItem.setPrice(book.getPrice());
        findItem.setStockQuantity(book.getStockQuantity());

        //itemRepository.save(findItem);
    }

    @Transactional
    public void updateItem2(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findById(itemId);
        findItem.setItemName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        //itemRepository.save(findItem);
    }


    public Item findOne(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

}
