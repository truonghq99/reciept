package com.thomas.reciept.controller;

import com.thomas.reciept.model.Item;
import com.thomas.reciept.model.Reciept;
import com.thomas.reciept.model.RecieptItem;
import com.thomas.reciept.model.Supplier;
import com.thomas.reciept.service.ItemService;
import com.thomas.reciept.service.RecieptService;
import com.thomas.reciept.service.SequenceGeneratorService;
import com.thomas.reciept.service.SupplierService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/rest/api")
public class RecieptController {

    @Autowired
    RecieptService recieptService;
    @Autowired
    SupplierService supplierService;
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
        ArrayList<Supplier> listSupllier= (ArrayList<Supplier>) supplierService.getAllsupplier();
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

    @GetMapping("/supplier/create-supplier")
    public String createSupplier(Model model, Supplier supplier){
        model.addAttribute("supplier", supplier);
        return "Create/create-supplier";
    }

    @GetMapping("/supplier/suppliers")
    public String getAllsupplier(Supplier supplier, HttpSession session,Model model){
        ArrayList<Supplier> listsupplier= supplierService.getAllsupplier();
        model.addAttribute("listSupplier",listsupplier);
        session.setAttribute("listSupplier", listsupplier);
        return "list-supplier";
    }

    @PostMapping("/supplier/save-supplier")
    public String saveSupplier(Supplier supplier){
        supplier.setId(sequenceGeneratorService.generateSequence(Supplier.SEQUENCE_NAME));
        supplierService.savesupplier(supplier);
        return "index";
    }

    @RequestMapping("/supplier/suppliers/{id}")
    public String detailssupplier(@PathVariable int id,HttpSession session,Model model){
        ArrayList<Supplier> listsupplier= (ArrayList<Supplier>) session.getAttribute("listSupplier");
        Supplier supplier= new Supplier();
        for(int i=0;i< listsupplier.size();i++){
            if(listsupplier.get(i).getId()==id){
                supplier.setId(listsupplier.get(i).getId());
                supplier.setCompany(listsupplier.get(i).getCompany());
                supplier.setAddress(listsupplier.get(i).getAddress());
                supplier.setCooperateDate(listsupplier.get(i).getCooperateDate());
                break;
            }
        }
        model.addAttribute("supplier", supplier);
        return "Details/details-supplier";
    }

    @PostMapping("supplier/suppliers/update-supplier/{id}")
    public String updatesupplier(@PathVariable int id, Supplier supplier){
        supplierService.updatesupplier(id, supplier);
        return "index";
    }

    @RequestMapping("supplier/suppliers/delete-supplier/{id}")
    public String deletesupplier(@PathVariable int id){
        supplierService.deletesupplier(id);
        return "redirect:/";
    }
    

    @GetMapping("/reciept/reciepts")
    public ArrayList<Reciept> ListReciepts(){
        return recieptService.getAllReciepts();
    }
}
