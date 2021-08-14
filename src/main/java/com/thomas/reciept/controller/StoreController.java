package com.thomas.reciept.controller;

import com.thomas.reciept.model.Store;
import com.thomas.reciept.service.SequenceGeneratorService;
import com.thomas.reciept.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/rest/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/create-store")
    public String createStore(Store store, Model model){
        model.addAttribute("store",store);
        return "create-store";
    }

    @PostMapping("/save-store")
    public String saveStore(Store store){
        store.setId(sequenceGeneratorService.generateSequence(Store.SEQUENCE_NAME));
        storeService.saveStore(store);
        return "index";
    }

    @GetMapping("/stores")
    public String getAllStore(Store store, HttpSession session,Model model){
        ArrayList<Store> listStore= storeService.getAllStore();
        System.out.println(listStore.toString());
        model.addAttribute("listStore",listStore);
        session.setAttribute("listStore", listStore);
        return "list-store";
    }

    @GetMapping("/stores/{id}")
    public String detailsStore(@PathVariable int id,HttpSession session,Model model){
        ArrayList<Store> listStore= (ArrayList<Store>) session.getAttribute("listStore");
        Store store= new Store();
        for(int i=0;i< listStore.size();i++){
            if(listStore.get(i).getId()==id){
                store.setId(listStore.get(i).getId());
                store.setStatus(listStore.get(i).getStatus());
                store.setAddress(listStore.get(i).getAddress());
                store.setOpenDate(listStore.get(i).getOpenDate());
                store.setPhoneNumber(listStore.get(i).getPhoneNumber());
                break;
            }
        }
        model.addAttribute("store", store);
        return "details-store";
    }

    @PostMapping("/stores/update-store/{id}")
    public String updateStore(@PathVariable int id, Store store){
        storeService.updateStore(id, store);
        return "index";
    }

    @RequestMapping("/stores/delete-store/{id}")
    public String deleteStore(@PathVariable int id){
        storeService.deleteStore(id);
        return "redirect:/";
    }
}
