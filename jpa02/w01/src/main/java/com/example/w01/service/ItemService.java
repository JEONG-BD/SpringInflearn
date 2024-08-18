package com.example.w01.service;

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

    public Item findOne(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

}
