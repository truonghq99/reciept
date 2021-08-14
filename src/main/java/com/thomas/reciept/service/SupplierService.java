package com.thomas.reciept.service;

import com.thomas.reciept.model.Supplier;
import com.thomas.reciept.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier savesupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public void updatesupplier(int id, Supplier supplier){
        if(supplierRepository.existsById(id)){
            if(supplier.getId()==id){
                supplierRepository.save(supplier);
            }
        }
    }

    public ArrayList<Supplier> getAllsupplier(){
        ArrayList<Supplier> listsupplier= new ArrayList<>();
        listsupplier= (ArrayList<Supplier>) supplierRepository.findAll();
        return listsupplier;
    }

    public void deletesupplier(int id){
        if(supplierRepository.existsById(id)){
            supplierRepository.deleteById(id);
            log.info("Item delete successfully");
        }else{
            log.info("Item does not exist");
        }
    }

}
