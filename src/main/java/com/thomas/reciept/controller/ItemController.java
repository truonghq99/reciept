package com.thomas.reciept.controller;

import com.thomas.reciept.model.Book;
import com.thomas.reciept.model.Item;
import com.thomas.reciept.repository.BookRepository;
import com.thomas.reciept.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/rest/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/create-item")
    public String createItem(Book book, Model model){
        model.addAttribute("book",book);
        return "create-item";
    }

    @PostMapping("/saveItem")
    public String saveItem(@RequestBody Item item){
        log.info("save item from item controller");
        return "index";
    }
    @PostMapping("/saveBook")
    public String saveBook(Book book){
        book.setId(10);
        log.info("save book from item controller");
        bookRepository.save(book);
        return "index";
    }


    @GetMapping("/")
    public ArrayList<Item> listItem(){
        log.info("List Item from item controller");
        return itemService.getAllItems();
    }
}
