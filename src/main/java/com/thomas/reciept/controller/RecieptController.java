package com.thomas.reciept.controller;

import com.thomas.reciept.model.Item;
import com.thomas.reciept.model.Reciept;
import com.thomas.reciept.model.RecieptItem;
import com.thomas.reciept.model.Supplier;
import com.thomas.reciept.repository.SupplierRepository;
import com.thomas.reciept.service.ItemService;
import com.thomas.reciept.service.RecieptService;
import com.thomas.reciept.service.SequenceGeneratorService;

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
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @PostMapping("/reciept/save-reciept")
    public String saveReciept(Reciept reciept) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyMMddhhmmssMs");
        Date date= new Date();
        String id= sdf.format(date);
        reciept.setId(id);
        for(int i=0;i<reciept.getListRecieptItem().size();i++) {
            System.out.println("vitri: "+i);
            if (reciept.getListRecieptItem().get(i).getTotalPrice() <= 0.0) {
                System.out.println(reciept.getListRecieptItem().get(i).getTotalPrice());
                reciept.getListRecieptItem().remove(i);
                i--;

            }
            else {
                reciept.getListRecieptItem().get(i).setId(sequenceGeneratorService.generateSequence(RecieptItem.SEQUENCE_NAME));
                itemService.updateQuantityItem(reciept.getListRecieptItem().get(i).getItem() ,reciept.getListRecieptItem().get(i).getQuantity());
            }

        }
        recieptService.saveReciept(reciept);
        return "index";
    }

    @GetMapping("/")
    public String showIndex(){
        return "";
    }

    @RequestMapping(value="/reciept/create-reciept", method = RequestMethod.GET)
    public String showReciept(Reciept reciept, Model model){
        log.info("get reciept in controller");
        ArrayList<Item> listItem= itemService.getAllItems();
        ArrayList<Supplier> listSupllier= (ArrayList<Supplier>) supplierRepository.findAll();
        model.addAttribute("listSupplier",listSupllier);
        ArrayList<RecieptItem> listRecieptItem= new ArrayList<RecieptItem>();
        for(int i=0;i<listItem.size();i++){
            System.out.println(listItem.get(i));
            RecieptItem ri= new RecieptItem();
            ri.setItem(listItem.get(i));
            listRecieptItem.add(ri);
        }
        reciept.setListRecieptItem(listRecieptItem);
        model.addAttribute("reciept",reciept);
        return "reciept";
    }

    @RequestMapping(value="/supplier/create-supplier", method = RequestMethod.GET)
    public String createSupplier(Supplier supplier,Model model) {
        model.addAttribute("supplier", supplier);
        return "create-supplier";
    }

    @PostMapping("/supplier/save-supplier")
    public String saveSupplier(Supplier supplier){
        supplier.setId(sequenceGeneratorService.generateSequence(Supplier.SEQUENCE_NAME));
        supplierRepository.save(supplier);
        return "index";
    }

    @GetMapping("/reciept/reciepts")
    public ArrayList<Reciept> ListReciepts(){
        return recieptService.getAllReciepts();
    }
}
