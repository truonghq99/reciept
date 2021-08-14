package com.thomas.reciept.service;

import com.thomas.reciept.model.Store;
import com.thomas.reciept.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store saveStore(Store store){
        return storeRepository.save(store);
    }

    public void updateStore(int id, Store store){
        if(storeRepository.existsById(id)){
            if(store.getId()==id){
                storeRepository.save(store);
            }
        }
    }

    public ArrayList<Store> getAllStore(){
        ArrayList<Store> listStore= new ArrayList<>();
        listStore= (ArrayList<Store>) storeRepository.findAll();
        return listStore;
    }

    public void deleteStore(int id){
        if(storeRepository.existsById(id)){
            storeRepository.deleteById(id);
            log.info("Item delete successfully");
        }else{
            log.info("Item does not exist");
        }
    }

}
