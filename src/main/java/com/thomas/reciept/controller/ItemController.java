package com.thomas.reciept.controller;

import com.thomas.reciept.model.Book;
import com.thomas.reciept.model.Clothes;
import com.thomas.reciept.model.Electronics;
import com.thomas.reciept.model.Inventory;
import com.thomas.reciept.model.Item;
import com.thomas.reciept.model.RecieptStore;
import com.thomas.reciept.model.Store;
import com.thomas.reciept.service.InventoryService;
import com.thomas.reciept.service.ItemService;
import com.thomas.reciept.service.RecieptService;
import com.thomas.reciept.service.SequenceGeneratorService;
import com.thomas.reciept.service.StoreService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/rest/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private RecieptService recieptService;

    @GetMapping("/create-item")
    public String createItem(Book book,Electronics electronics, Clothes clothes, Model model){
        model.addAttribute("book",book);
        model.addAttribute("electronics",electronics);
        model.addAttribute("clothes",clothes);
        return "Create/create-item";
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
        for(int i=0;i< listItem.size();i++){
            if(listItem.get(i).getId()==id){
                if(listItem.get(i).getType().equalsIgnoreCase("book")){ 
                    Book item= (Book) listItem.get(i);
                    model.addAttribute("item", item);   
                    return "Details/details-item-book";
                }else if(listItem.get(i).getType().equalsIgnoreCase("clothes")){
                    Clothes item= (Clothes)listItem.get(i);
                    model.addAttribute("item", item);
                    return "Details/details-item-clothes";
                }else{
                    Electronics item= (Electronics)listItem.get(i);
                    model.addAttribute("item", item);
                    return "Details/details-item-electronics";
                } 
            }
        }
        return "";
    }
    @PostMapping("/items/update-book/{id}")
    public String updateBook(@PathVariable int id, Book book){
        itemService.updateBook(id, book);
        return "redirect:/";
    }
    @PostMapping("/items/update-clothes/{id}")
    public String updateClothes(@PathVariable int id, Clothes clothes){
        itemService.updateClothes(id, clothes);
        return "redirect:/";
    }
    @PostMapping("/items/update-electronics/{id}")
    public String updateElectronics(@PathVariable int id, Electronics electronics){
        itemService.updateElectronics(id, electronics);
        return "redirect:/";
    }

    @RequestMapping("/items/delete-item/{id}")
    public String deleteItem(@PathVariable int id){
        itemService.deleteItem(id);
        return "redirect:/";
    }

    @RequestMapping(value="/items/export-item", method = RequestMethod.GET)
    public String showExportItem(Model model, RecieptStore recieptStore){
        ArrayList<Item> listItem= itemService.getAllItems();
        ArrayList<Store> listStore= storeService.getAllStore();
        recieptStore.setListItem(listItem);
        model.addAttribute("recieptStore", recieptStore);
        model.addAttribute("listStore", listStore);
        return "export-item";
    }
    @PostMapping("/items/save-export-reciept")
    public String saveExportReciept(RecieptStore recieptStore) {
        for(int i=0;i<recieptStore.getListItem().size();i++) {
            if (recieptStore.getListItem().get(i).getQuantity() <=0) {
                recieptStore.getListItem().remove(i);
                i--;
            }
            else {
                itemService.updateExportQuantityItem(recieptStore.getListItem().get(i) ,recieptStore.getListItem().get(i).getQuantity());
            }

        }
        Date date= new Date();
        recieptStore.setDate(date);
        recieptService.saveRecieptStore(recieptStore);
        Inventory inventory= new Inventory();
        inventory.setId(recieptStore.getStore().getId());
        inventory.setListItem(recieptStore.getListItem());
        inventory.setStore(recieptStore.getStore());
        inventoryService.updateInventory(inventory);
        return "index";
    }

}
