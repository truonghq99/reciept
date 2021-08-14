package com.thomas.reciept.controller;

import com.thomas.reciept.model.Book;
import com.thomas.reciept.model.Clothes;
import com.thomas.reciept.model.Electronics;
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

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/rest/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/create-item")
    public String createItem(Book book,Electronics electronics, Clothes clothes, Model model){
        model.addAttribute("book",book);
        model.addAttribute("electronics",electronics);
        model.addAttribute("clothes",clothes);
        return "create-item";
    }

    @PostMapping("/save-book")
    public String saveBook(Book book){
        log.info("save book from item controller");
        book.setId(sequenceGeneratorService.generateSequence(Item.SEQUENCE_NAME));
        book.setType("book");
        itemService.saveBook(book);
        return "index";
    }
    @PostMapping("/save-clothes")
    public String saveClothes(Clothes clothes){
        log.info("save clothes from item controller");
        clothes.setId(sequenceGeneratorService.generateSequence(Item.SEQUENCE_NAME));
        clothes.setType("clothes");
        itemService.saveClothes(clothes);
        return "index";
    }
    @PostMapping("/save-electronics")
    public String saveElectronics(Electronics electronics){
        log.info("save electronics from item controller");
        electronics.setId(sequenceGeneratorService.generateSequence(Item.SEQUENCE_NAME));
        electronics.setType("electronics");
        itemService.saveElectronics(electronics);
        return "index";
    }
    @GetMapping("/items")
    public String getAllItem(Item item, HttpSession session,Model model){
        ArrayList<Item> listItem= itemService.getAllItems();
        model.addAttribute("listItem",listItem);
        session.setAttribute("listItem", listItem);
        return "list-item";
    }

    @GetMapping("/items/{id}")
    public String detailsItem(@PathVariable int id,HttpSession session,Model model){
        ArrayList<Item> listItem= (ArrayList<Item>) session.getAttribute("listItem");
        Item item= new Item();
        for(int i=0;i< listItem.size();i++){
            if(listItem.get(i).getId()==id){
                item = listItem.get(i);
            }
        }
        model.addAttribute("item", item);
        if(item.getType().equalsIgnoreCase("book")){    
            return "Details/details-item-book";
        }else if(item.getType().equalsIgnoreCase("clothes")){
            return "Details/details-item-clothes";
        }else{
            return "Details/details-item-electronics";
        } 
    }

    @PostMapping("/items/update-item/{id}")
    public String updateItem(@PathVariable int id, Book book){
        
        return "index";
    }

    @RequestMapping("/items/delete-item/{id}")
    public String deleteItem(@PathVariable int id){
        itemService.deleteItem(id);
        return "redirect:/";
    }

}
