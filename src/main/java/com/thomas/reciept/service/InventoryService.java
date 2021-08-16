package com.thomas.reciept.service;

import java.util.ArrayList;
import java.util.Optional;

import com.thomas.reciept.model.Inventory;
import com.thomas.reciept.model.Item;
import com.thomas.reciept.model.Store;
import com.thomas.reciept.repository.InventoryRepository;
import com.thomas.reciept.repository.ItemRepository;
import com.thomas.reciept.repository.MongoDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MongoDB mongoDb;

    public Inventory findInventoryById(int id){
        if(inventoryRepository.findInventoryById(id)==null){
            return null;
        }else{
            return inventoryRepository.findInventoryById(id);
        }
    }

    public void updateInventory(Inventory inventory){
        Inventory inven =inventoryRepository.findInventoryById(inventory.getId());
        if(inven == null){
            // Inventory i = new Inventory();
            // ArrayList<Item>list= (ArrayList<Item>) itemRepository.findAll();
            // i.setListItem(list);
            // for(int j=0; j<list.size(); j++){
            //     i.getListItem().get(j).setQuantity(0);
            // }
            // i.setStore(inventory.getStore());
            inventoryRepository.save(inventory);
        }else{
            boolean check=false;
            for(int i=0;i<inventory.getListItem().size();i++){
                for(int j=0;j<inven.getListItem().size();j++){
                    if(inventory.getListItem().get(i).getId()==inven.getListItem().get(j).getId()){
                        int quantity = inven.getListItem().get(j).getQuantity();
                        int newQuantity= inventory.getListItem().get(i).getQuantity();
                        inven.getListItem().get(j).setQuantity(quantity+newQuantity);
                        check=true;
                    }
                }
                if(check==true){
                    inventory.getListItem().remove(i);
                    i--;
                }else if(check==false){
                    System.out.println("add item");
                    inven.getListItem().add(inventory.getListItem().get(i));
                }
                check=false;
            }
            System.out.println(inven.getListItem());
            inventoryRepository.save(inven);
        }
    }

}
