package com.thomas.reciept.controller;

import com.thomas.reciept.model.Book;
import com.thomas.reciept.model.Item;
import com.thomas.reciept.repository.BookRepository;
import com.thomas.reciept.service.ItemService;
import com.thomas.reciept.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/create-item")
    public String createItem(Book book, Model model){
        model.addAttribute("book",book);
        return "create-item";
    }

    @PostMapping("/saveBook")
    public String saveBook(Book book){
        log.info("save book from item controller");
        book.setId(sequenceGeneratorService.generateSequence(Item.SEQUENCE_NAME));
        book.setType("book");
        itemService.saveBook(book);

        return "index";
    }



    @GetMapping("/")
    public ArrayList<Item> listItem(){
        log.info("List Item from item controller");
        return itemService.getAllItems();
    }
}
