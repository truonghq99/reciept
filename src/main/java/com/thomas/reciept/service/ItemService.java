package com.thomas.reciept.service;

import com.thomas.reciept.model.Book;
import com.thomas.reciept.model.Item;
import com.thomas.reciept.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    public ArrayList<Item> getAllItems(){
        return (ArrayList<Item>) itemRepository.findAll();
    }

    public void deleteItem(int id){
        itemRepository.deleteById(id);
    }
}
