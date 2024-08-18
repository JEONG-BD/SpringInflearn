package com.example.w01.controller;

import com.example.w01.domain.item.Book;
import com.example.w01.domain.item.Item;
import com.example.w01.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createItemForm(Model model){
        model.addAttribute("form", new BookForm());

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form, BindingResult result){
        if(result.hasErrors()){
            return "items/createItemForm";
        }
        Book book = new Book();
        book.setItemName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.add(book);

        return "redirect:/";
    }
//
//    @GetMapping("/items")
//    public String itemList(Model model){
//
//        List<Item> items = itemService.findAll();
//        model.addAttribute("items", items);
//
//        return "/items/itemList";
//    }

    @GetMapping("/items")
    public String list(Model model){

        List<Item> items = itemService.findAll();
        for(Item it : items){
            System.out.println(it);
        }

        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/items/{id}/edit")
    public String updateItemForm(@PathVariable("id") Long id, Model model){
        Book findItem = (Book)itemService.findOne(id);

        BookForm form = new BookForm();
        form.setId(findItem.getId());
        form.setName(findItem.getItemName());
        form.setPrice(findItem.getPrice());
        form.setAuthor(findItem.getAuthor());
        form.setIsbn(findItem.getIsbn());
        form.setStockQuantity(findItem.getStockQuantity());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping("/items/{id}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form){

        Book newBook = new Book();
        newBook.setAuthor(form.getAuthor());
        newBook.setItemName(form.getName());
        newBook.setPrice(form.getPrice());
        newBook.setStockQuantity(form.getStockQuantity());
        newBook.setIsbn(form.getIsbn());
        newBook.setId(form.getId());

        itemService.add(newBook);

        return "redirect:/items";
    }


}
