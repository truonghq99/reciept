package com.thomas.reciept.service;

import com.thomas.reciept.model.Book;
import com.thomas.reciept.model.Clothes;
import com.thomas.reciept.model.Electronics;
import com.thomas.reciept.model.Item;
import com.thomas.reciept.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private ElectronicsRepository electronicsRepository;
    @Autowired
    private MongoDB repository;

    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    //CREATE ITEM
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }
    public Electronics saveElectronics(Electronics electronics){
        return electronicsRepository.save(electronics);
    }
    public Clothes saveClothes(Clothes clothes){
        return clothesRepository.save(clothes);
    }
    //END CREATE ITEM


    //UPDATE ITEM
    public void updateBook(int id, Book book){
        Book b= new Book();
        if(book.getId()==id){
            if(bookRepository.existsById(id)){
                b.setId(id);
                b.setAuthor(book.getAuthor());
                b.setType(book.getType());
                b.setCategory(book.getCategory());
                b.setMfgDate(book.getMfgDate());
                b.setPrice(book.getPrice());
                b.setPublisher(book.getPublisher());
                bookRepository.save(b);
                log.info("Update book successfully");
            }else{
                log.info("Book ID does not exist");
            }
        }
    }
    public void updateElectronics(int id, Electronics electronics){
        Electronics e= new Electronics();
        if(electronics.getId()==id){
            if(bookRepository.existsById(id)){
                e.setId(id);
                e.setDescription(electronics.getDescription());
                e.setType(e.getType());
                e.setBrand(e.getBrand());
                e.setMfgDate(electronics.getMfgDate());
                e.setPrice(electronics.getPrice());
                e.setVersion(electronics.getVersion());
                electronicsRepository.save(e);
                log.info("Update electronics successfully");
            }else{
                log.info("Electronics ID does not exist");
            }
        }
    }
    public void updateClothes(int id, Clothes clothes){
        Clothes c= new Clothes();
        if(clothes.getId()==id){
            if(clothesRepository.existsById(id)){
                c.setId(id);
                c.setMaterial(clothes.getMaterial());
                c.setType(clothes.getType());
                c.setBrand(clothes.getBrand());
                c.setMfgDate(clothes.getMfgDate());
                c.setPrice(clothes.getPrice());
                c.setSize(clothes.getSize());
                clothesRepository.save(c);
                log.info("Update clothes successfully");
            }else{
                log.info("clothes ID does not exist");
            }
        }
    }

    public void updateQuantityItem(Item item, int quantity){
        repository.updateQuantity(item.getId(),quantity);
    }
    //END UPDATE ITEM


    //FIND ITEM
    public ArrayList<Item> getAllItems(){
        return (ArrayList<Item>) itemRepository.findAll();
    }

    public Item findItemById(int id){
        Item item= new Item();
        if(itemRepository.existsById(id)){
            return null;
        }
        return null;
    }
    //END FIND ITEM


    //DELETE ITEM
    public void deleteItem(int id){
        if(itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            log.info("Delete item with ID: "+id+" successfully");
        }else{
            log.info("Item id does not exist");
        }
    }
    //END DELETE ITEM
}
