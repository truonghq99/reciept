package com.thomas.reciept.controller;

import com.thomas.reciept.model.Item;
import com.thomas.reciept.model.Reciept;
import com.thomas.reciept.model.RecieptItem;
import com.thomas.reciept.model.Supplier;
import com.thomas.reciept.repository.SupplierRepository;
import com.thomas.reciept.service.ItemService;
import com.thomas.reciept.service.RecieptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/rest/api")
public class RecieptController {

    @Autowired
    RecieptService recieptService;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ItemService itemService;

    @PostMapping("/reciept/save-reciept")
    public Reciept saveReciept(@RequestBody Reciept reciept){
        SimpleDateFormat sdf= new SimpleDateFormat("yyMMddhhmmssMs");
        Date date= new Date();
        String id= sdf.format(date);
        reciept.setId(id);
        return recieptService.saveReciept(reciept);
    }

    @GetMapping("/")
    public String showIndex(){
        return "";
    }

    @RequestMapping(value="/reciept/create-reciept", method = RequestMethod.GET)
    public String showReciept(Reciept reciept, Model model){
        ArrayList<Item> listItem= itemService.getAllItems();
        ArrayList<Supplier> listSupllier= (ArrayList<Supplier>) supplierRepository.findAll();
        model.addAttribute("listSupplier",listSupllier);
//        ArrayList<RecieptItem> listRecieptItem= new ArrayList<RecieptItem>();
//        for(int i=0;i<listItem.size();i++){
//            System.out.println(listItem.get(i).toString());
//            RecieptItem ri= new RecieptItem();
//            ri.setItem(listItem.get(i));
//            listRecieptItem.add(ri);
//        }
//        reciept.setListItem(listRecieptItem);
        model.addAttribute("reciept",reciept);
        return "reciept";
    }

    @PostMapping("/supplier/save-supplier")
    public Supplier saveSupplier(@RequestBody Supplier supplier){
        return supplierRepository.save(supplier);
    }

    @GetMapping("/reciept/reciepts")
    public ArrayList<Reciept> ListReciepts(){
        return recieptService.getAllReciepts();
    }
}
